package lmr.rcd.models.hierarchy.storage

import lmr.rcd.models.decorators.EffectDecorator
import lmr.rcd.models.entity.Effect
import lmr.rcd.models.entity.EffectInterface
import java.lang.IllegalArgumentException

class EffectStorage
    @JvmOverloads constructor(effects: List<EffectInterface> = listOf())
: EffectStorageInterface {
    private val effectsByIdByType = mutableMapOf<Short, MutableMap<Int, Effect>>()

    init { addEffects(effects) }

    override fun addEffect(effect: Effect) {
        if (effect.parent != null)
            throw IllegalArgumentException("Effect is already added to ${effect.parent}")

        getEffectsMap(effect.typeId)[effect._id] = effect
        effect._attachTo(this)
    }

    override fun removeEffect(effect: Effect) {
        if (effect.parent != this)
            throw IllegalArgumentException("Effect is not attached to this object")

        getEffectsMap(effect.typeId).remove(effect._id)
        effect._detach()
    }

    override fun fetchOwnEffects(): List<Effect> =
            effectsByIdByType.values.flatMap { it.values }

    private fun getEffectsMap(typeId: Short): MutableMap<Int, Effect> =
            effectsByIdByType.getOrPut(typeId) { mutableMapOf() }
}