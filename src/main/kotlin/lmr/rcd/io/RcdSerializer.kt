package lmr.rcd.io

import lmr.rcd.models.entity.Actor
import lmr.rcd.models.entity.Entity
import lmr.rcd.models.entity.Test
import lmr.rcd.models.entity.Update
import lmr.rcd.models.hierarchy.*
import java.io.DataOutputStream
import java.io.FileOutputStream
import java.nio.file.Path

class RcdSerializer {
    private lateinit var stream: DataOutputStream

    fun serializeWorld(world: World, outputFilePath: Path) {
        stream = DataOutputStream(FileOutputStream(outputFilePath.toString()))

        stream.writeShort(0) // unknown header
        world.zones.forEach(this::serializeZone)
    }

    private fun serializeZone(zone: Zone) {
        val nameBytes = zone.name.toByteArray(Charsets.UTF_16)
        stream.writeByte(nameBytes.size)

        val effects = zone.fetchOwnEffects()
        stream.writeShort(effects.size)

        nameBytes.forEach { stream.writeByte(it.toInt()) }

        effects.forEach(this::serializeEntity)

        zone.scenes.forEach(this::serializeScene)
    }

    private fun serializeScene(scene: Scene) {
        val effects = scene.fetchOwnEffects()
        stream.writeShort(effects.size)
        effects.forEach(this::serializeEntity)

        scene.screens.forEach(this::serializeScreen)
    }

    private fun serializeScreen(screen: Screen) {
        val nameBytes = screen.name.toByteArray(Charsets.UTF_16)
        stream.writeByte(nameBytes.size)

        val effects = screen.fetchOwnEffects()
        val actors = screen.fetchOwnActors()
        stream.writeShort(effects.size + actors.size)
        stream.writeByte(effects.size)

        effects.forEach(this::serializeEntity)
        actors.forEach(this::serializeEntity)

        nameBytes.forEach { stream.writeByte(it.toInt()) }

        repeat(4) { i -> serializeExit(screen.exits[i]) }
    }

    private fun serializeExit(exit: ScreenCoords) {
        stream.writeByte(exit.zoneIdx.toInt())
        stream.writeByte(exit.sceneIdx.toInt())
        stream.writeByte(exit.screenIdx.toInt())
    }

    private fun serializeEntity(entity: Entity) {
        stream.writeShort(entity.typeId.toInt())

        val testsCount = entity.tests.size
        val updatesCount = entity.updates.size
        val paramsCount = entity.params.size

        val operationsLengthsBits = BitsProcessor()
        operationsLengthsBits.put(updatesCount, 4)
        operationsLengthsBits.put(testsCount, 4)
        stream.writeByte(operationsLengthsBits.value)
        stream.writeByte(paramsCount)

        if (entity is Actor) {
            stream.writeShort(entity.x().toInt())
            stream.writeShort(entity.y().toInt())
        }

        entity.tests.forEach(this::serializeTest)
        entity.updates.forEach(this::serializeUpdate)
        entity.params.forEach { stream.writeShort(it.toInt()) }
    }

    private fun serializeTest(test: Test) {
        stream.writeShort(test.flag.toInt())
        stream.writeByte(test.value.toInt())
        stream.writeByte(test.operator.value.toInt())
    }

    private fun serializeUpdate(update: Update) {
        stream.writeShort(update.flag.toInt())
        stream.writeByte(update.value.toInt())
        stream.writeByte(update.operator.value.toInt())
    }

    private companion object Static {
        @JvmStatic fun serialize(world: World, outputFilePath: Path) =
            RcdSerializer().serializeWorld(world, outputFilePath)
    }
}