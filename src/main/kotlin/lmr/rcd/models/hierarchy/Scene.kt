package lmr.rcd.models.hierarchy

import lmr.rcd.models.entity.Effect
import lmr.rcd.models.hierarchy.storage.EffectStorage
import lmr.rcd.models.hierarchy.storage.EffectStorageImpl

class Scene
    @JvmOverloads constructor(
        val screens: MutableList<Screen> = mutableListOf(),
        effects: List<Effect> = listOf()
    )
: EffectStorage by EffectStorageImpl(effects) {
    // TODO: query methods
    //     getParentZone()
    //     actors(screenPredicate: Screen -> Boolean, wrapper: W): List<W>
    //     actors(screenPredicate: Screen -> Boolean, wrappers: List<W>): List<Actor>
    //     childEntities()
    //     sceneEffects()
    //     childEffects()
    //     plus all Screen queries
}