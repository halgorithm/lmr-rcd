package lmr.rcd.io

import lmr.rcd.models.entity.*
import lmr.rcd.models.hierarchy.*
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.file.Files
import java.nio.file.Path

class RcdParser {
    private lateinit var buffer: ByteBuffer

    @JvmOverloads @Throws(IOException::class)
    fun parseFile(rcdScriptPath: Path, worldScreenCounts: List<List<Byte>> = vanillaScreenCounts): World {
        val fileBytes = Files.readAllBytes(rcdScriptPath)
        buffer = ByteBuffer.wrap(fileBytes).order(ByteOrder.BIG_ENDIAN)

        val unknown = buffer.short

        val zones = MutableList(worldScreenCounts.size) { i ->
            log("zone $i", 0)
            parseZone(worldScreenCounts[i])
        }

        return World(zones)
    }

    private fun parseZone(zoneScreenCounts: List<Byte>): Zone {
        val internalNameLength = buffer.get().toInt()
        val effectsCount = buffer.short.toInt()
        val name = parseString(internalNameLength)
//        log(name, 0)

        val effects = List(effectsCount) { parseEffect() }

        val scenes = MutableList(zoneScreenCounts.size) { i ->
            log("scene $i", 1)
            parseScene(zoneScreenCounts[i])
        }

        return Zone(name, scenes, effects)
    }

    private fun parseScene(screensCount: Byte): Scene {
        val effectsCount = buffer.short.toInt()
        val effects = List(effectsCount) { parseEffect() }

        val screens = MutableList(screensCount.toInt()) { i ->
            log("screen $i", 2)
            parseScreen()
        }

        return Scene(screens, effects)
    }

    private fun parseScreen(): Screen {
        val internalNameLength = buffer.get().toInt()
        val entitiesCount = buffer.short.toInt()

        val effectsCount = buffer.get().toInt()
        val effects = List(effectsCount) { parseEffect() }

        val actorsCount = entitiesCount - effectsCount
        val actors = List(actorsCount) { parseActor() }

        val name = parseString(internalNameLength)
//        log(name, 0)

        val exits = Array(4) { parseExit() }

        return Screen(name, effects, actors, exits)
    }

    private fun parseEntityData(hasPosition: Boolean): Pair<RcdObjectData, Position?> {
        val typeId = buffer.short

        val operationsLengthsBits = BitsProcessor(buffer.get().toInt())
        val updatesCount = operationsLengthsBits.get(4)
        val testsCount = operationsLengthsBits.get(4)
        val paramsCount = buffer.get().toInt()

        var logHeader = "entity ${RcdObjectData.toTypeIdStr(typeId)}"

        var pos: Position? = null
        if (hasPosition) {
            pos = Position(buffer.short, buffer.short)
            logHeader += " @ ${pos.x}, ${pos.y}"
        }

//        log(logHeader, 3)

        val tests = MutableList(testsCount) {
            Test(buffer.short, buffer.get(), TestOperator.valueOf(buffer.get()))
        }

        val updates = MutableList(updatesCount) {
            Update(buffer.short, buffer.get(), UpdateOperator.valueOf(buffer.get()))
        }

        val params = MutableList(paramsCount) { buffer.short }

        val logParts =
            arrayOf(
                tests.joinToString("\n"),
                updates.joinToString("\n"),
                params.joinToString("\n")
            ).filter { it.isNotEmpty() }
            .joinToString("\n")

//        log(logParts, 4)

        val obj = RcdObjectData(typeId, params, tests, updates)

        return Pair(obj, pos)
    }

    private fun parseEffect(): Effect {
        val data = parseEntityData(false).first
        return Effect(data)
    }

    private fun parseActor(): Actor {
        val (data, pos) = parseEntityData(true)
        return Actor(data, pos!!)
    }

    private fun parseExit() = ScreenCoords(
            buffer.get(), buffer.get(), buffer.get()
    )

    private fun parseString(length: Int): String {
        val strBytes = ByteArray(length)
        repeat(length) { i ->
            strBytes[i] = buffer.get()
        }

        return strBytes.toString(Charsets.UTF_16)
    }

    companion object Static {
        val vanillaScreenCounts = generateVanillaScreenCounts()

        @JvmStatic @JvmOverloads @Throws(IOException::class)
        fun parse(rcdScriptPath: Path, worldScreenCounts: List<List<Byte>> = vanillaScreenCounts) =
            RcdParser().parseFile(rcdScriptPath, worldScreenCounts)

        fun log(msg: String, depth: Int) {
            val indentedMsg = msg.prependIndent("  ".repeat(depth))
//            println(indentedMsg)
        }

        fun generateVanillaScreenCounts(): List<List<Byte>> {
            fun zeros(range: IntRange): Array<Byte> = Array<Byte>((range.last - range.first) + 1) { 0 }

            return listOf<List<Byte>>(
                listOf(2,2,2,2,3,1,2,2,2,2, *zeros(10..38)), // 0: Guidance
                listOf(3,2,2,1,3,3,2,2,2,2,4,2, *zeros(12..22)), // 1: Surface
                listOf(2,2,2,1,1,3,2,3,3,1, *zeros(10..19)), // 2: Mausoleum
                listOf(2,3,2,1,6,1,2,2,1, *zeros(9..27)), // 3: Sun
                listOf(3,1,2,5,1,2,2,2,2,0,1,1,1,1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0), // 4: Spring
                listOf(2,2,3,3,1,2,1,2,2,2, *zeros(10..23)), // 5: Inferno
                listOf(2,2,2,2,2,2,2,2,2,2, *zeros(10..30)), // 6: Extinction
                listOf(2,2,3,3,2,2,2,2,2,  2,2,3,3,2,2,3,3, *zeros(17..46)), // 7: Twin
                listOf(2,2,4,4,4,4,0,0,0,1,1,1,1, *zeros(13..22)), // 8: Endless
                listOf(2,2,2,2,2,2,2,2,2,2, *zeros(10..23)), // 9: Fake Shrine
                listOf(2,2,3,1,2,2,1,3,2,2,1,1, *zeros(12..32)), // 10: Illusion
                listOf(3,2,2,1,4,2,1,2,1,2, *zeros(10..38)), // 11: Graveyard
                listOf(2,2,2,1,4,2,2,1,1,2,1, *zeros(11..43)), // 12: Moonlight
                listOf(2,2,3,2,2,2,4,3,1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,1, *zeros(27..35)), // 13: Goddess
                listOf(3,2,2,2,2,2,2,2,3, *zeros(9..35)), // 14: Ruin
                listOf(2,2,2,2,1, *zeros(5..18)), // 15: Birth right
                listOf(2,2,2,2,3, *zeros(5..12)), // 16: Birth left
                listOf(2,2,2,1,2,2,1,2,2,2,2, *zeros(11..25)), // 17: Dimensional
                listOf(2,2,2,2,2,2,2,2,2,2, *zeros(10..14)), // 18: True shrine
                listOf(2,3,2,2,2), // 19: Retro Mausoleum
                listOf(2,3,2,2,2), // 20: Retro Guidance
                listOf(2,0), // 21: Retro Surface
                listOf(3,2,2,1,3,3,2,2,2,2,4,2, *zeros(12..22)), // 22: Night Surface
                listOf(1,2,2,1,2,2,2,1,2,2,2,1,2,1,2,2,1,1,2,1,1,1,2, *zeros(23..45)), // 23: Hell Temple 1
                listOf(2,1,2, *zeros(3..4)), // 24: Hell Temple 2,
                listOf(5,5,5,5,0) // 25: Lava Pit
            )
        }

        // TODO: generateVanillaTimeAttackScreenCounts()
        //     (12 total, each at data\ex\XX(room)guardian\mapdata\script.rcd)
    }
}