package lmr.rcd.models.entity

class Effect
    constructor(data: RcdObjectData)
: Entity(data), EffectInterface {
    override fun copy(): Effect = Effect(data.copy())
}