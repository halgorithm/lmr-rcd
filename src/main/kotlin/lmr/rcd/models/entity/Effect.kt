package lmr.rcd.models.entity

interface Effect : Entity {
    val impl: EffectImpl

    override fun copy(): Effect
}
