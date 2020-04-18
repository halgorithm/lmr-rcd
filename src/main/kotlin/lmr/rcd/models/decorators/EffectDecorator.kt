package lmr.rcd.models.decorators

import lmr.rcd.models.entity.Effect
import lmr.rcd.models.entity.EffectImpl

interface EffectDecorator : Effect {
    val impl: EffectImpl
}