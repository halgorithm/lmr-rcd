package lmr.rcd.models.hierarchy

import lmr.rcd.models.entity.Actor
import lmr.rcd.models.entity.Effect
import lmr.rcd.models.hierarchy.storage.ActorStorage
import lmr.rcd.models.hierarchy.storage.ActorStorageImpl
import lmr.rcd.models.hierarchy.storage.EffectStorage
import lmr.rcd.models.hierarchy.storage.EffectStorageImpl

class Screen
    @JvmOverloads constructor(
        internal var name: String = "",
        effects: List<Effect> = listOf(),
        actors: List<Actor> = listOf(),
        val exits: Array<ScreenCoords> = Array(4) { ScreenCoords() }
    )
: EffectStorage by EffectStorageImpl(effects),
    ActorStorage by ActorStorageImpl(actors)
{
    init {
        if (exits.size != 4)
            throw IllegalArgumentException("provided exits Array must be of length 4")
    }

    var above: ScreenCoords
        get() = exits[0]
        set(value) { exits[0] = value }

    var right: ScreenCoords
        get() = exits[1]
        set(value) { exits[1] = value }

    var below: ScreenCoords
        get() = exits[2]
        set(value) { exits[2] = value }

    var left: ScreenCoords
        get() = exits[3]
        set(value) { exits[3] = value }

    // REVIEW: allow Screen references for exits instead of/in addition to ScreenCoords?

    // TODO: entity queries
    //     getScene(): Scene
    //     getZone(): Zone
    //     getWorld(): World
    //     entities(): ScreenQuery<Entity>
    //     effects(): ScreenQuery<Effect>
    //     actors(): ScreenQuery<Actor>
}