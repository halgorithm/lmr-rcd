package lmr.rcd.models.entity

class EffectImpl
    constructor(data: RcdObjectData)
: EntityImpl(data), Effect {
    override fun copy(): EffectImpl = EffectImpl(data.copy())
}