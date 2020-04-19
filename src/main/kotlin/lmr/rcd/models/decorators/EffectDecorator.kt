package lmr.rcd.models.decorators

import lmr.rcd.models.entity.EffectInterface
import lmr.rcd.models.entity.Effect
import lmr.rcd.models.entity.ParamSpec

abstract class EffectDecorator
    (val effect: Effect)
: EntityDecorator(), EffectInterface by effect {
    protected fun <P: ParamSpec> toDebugString(expectedTypeId: Short, paramSpecs: Array<P>): String =
        EntityStringifier.stringifyEffect(effect, this::class.simpleName!!, expectedTypeId, paramSpecs)
}