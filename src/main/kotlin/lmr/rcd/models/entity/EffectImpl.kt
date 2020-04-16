package lmr.rcd.models.entity

class EffectImpl
    constructor(data: RcdObjectData)
: EntityImpl(data), Effect {
    override val impl = this

    override fun copy(): Effect = EffectImpl(data.copy())
}