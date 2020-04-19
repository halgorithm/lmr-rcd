package lmr.rcd.models.hierarchy

import lmr.rcd.models.entity.Effect
import lmr.rcd.models.entity.EffectInterface
import lmr.rcd.models.hierarchy.storage.EffectStorageInterface
import lmr.rcd.models.hierarchy.storage.EffectStorage

class Zone
    @JvmOverloads constructor(
        internal var name: String = "",
        val scenes: MutableList<Scene> = mutableListOf(),
        effects: List<EffectInterface> = listOf()
    )
: EffectStorageInterface by EffectStorage(effects) {
    // TODO: query methods
    //     screenEntities()
    //     zoneEffects()
    //     screenEffects()
    //     plus all Scene queries
}