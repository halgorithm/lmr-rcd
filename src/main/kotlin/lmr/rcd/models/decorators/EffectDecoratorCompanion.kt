package lmr.rcd.models.decorators

import lmr.rcd.models.entity.*

abstract class EffectDecoratorCompanion<T : EffectDecorator, P : ParamSpec>
    (typeId: Short, paramSpecs: Array<P>)
: EntityDecoratorCompanion<P>(typeId, paramSpecs) {
    protected fun generateDefaultEffect() = Effect(
        RcdObjectData(typeId, defaultParams.toMutableList())
    )

    abstract fun wrap(effect: Effect): T
}