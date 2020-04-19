package lmr.rcd.models.decorators

import lmr.rcd.models.entity.EffectInterface
import lmr.rcd.models.entity.Effect

interface EffectDecorator : EffectInterface {
    val effect: Effect
}