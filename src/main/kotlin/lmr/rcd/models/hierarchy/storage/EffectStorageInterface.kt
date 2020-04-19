package lmr.rcd.models.hierarchy.storage

import lmr.rcd.models.decorators.EffectDecorator
import lmr.rcd.models.entity.Effect
import lmr.rcd.models.entity.EffectInterface

interface EffectStorageInterface {
    fun fetchOwnEffects(): List<Effect>

    fun addEffect(effect: Effect)
    fun addEffect(decorator: EffectDecorator) = addEffect(decorator.effect)
    fun removeEffect(effect: Effect)
    fun removeEffect(decorator: EffectDecorator) = removeEffect(decorator.effect)

    fun addEffects(effects: List<EffectInterface>) {
        effects.forEach {
            if (it is EffectDecorator) addEffect(it.effect)
            else addEffect(it as Effect)
        }
    }

    fun removeEffects(effects: List<EffectInterface>) {
        effects.forEach {
            if (it is EffectDecorator) removeEffect(it.effect)
            else removeEffect(it as Effect)
        }
    }
}