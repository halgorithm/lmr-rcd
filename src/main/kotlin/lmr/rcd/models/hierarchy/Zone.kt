package lmr.rcd.models.hierarchy

import lmr.rcd.models.entity.Effect
import lmr.rcd.models.hierarchy.storage.EffectStorage
import lmr.rcd.models.hierarchy.storage.EffectStorageImpl

class Zone
    @JvmOverloads constructor(
        internal var name: String = "",
        val scenes: MutableList<Scene> = mutableListOf(),
        effects: List<Effect> = listOf()
    )
: EffectStorage by EffectStorageImpl(effects) {
    // TODO: query methods
    //     screenEntities()
    //     zoneEffects()
    //     screenEffects()
    //     plus all Scene queries
}