package lmr.rcd.models.hierarchy

import lmr.rcd.models.entity.EffectInterface
import lmr.rcd.models.hierarchy.storage.EffectStorageInterface
import lmr.rcd.models.hierarchy.storage.EffectStorage

class Scene
    @JvmOverloads constructor(
        val screens: MutableList<Screen> = mutableListOf(),
        effects: List<EffectInterface> = listOf()
    )
: EffectStorageInterface by EffectStorage(effects) {
    // TODO: query methods
    //     getParentZone()
    //     actors(screenPredicate: Screen -> Boolean, wrapper: W): List<W>
    //     actors(screenPredicate: Screen -> Boolean, wrappers: List<W>): List<Actor>
    //     childEntities()
    //     sceneEffects()
    //     childEffects()
    //     plus all Screen queries
}