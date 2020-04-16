package lmr.rcd.models.hierarchy.storage

import lmr.rcd.models.entity.Effect

interface EffectStorage {
    fun addEffect(effect: Effect)
    fun removeEffect(effect: Effect)
    fun fetchOwnEffects(): List<Effect>
}