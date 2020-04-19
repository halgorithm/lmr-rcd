package lmr.rcd.models.decorators

import lmr.rcd.models.entity.*

abstract class ActorDecoratorCompanion<T : ActorDecorator, P : ParamSpec>
    (typeId: Short, paramSpecs: Array<P>)
: EntityDecoratorCompanion<P>(typeId, paramSpecs) {
    protected fun generateDefaultImpl() = ActorImpl(
        RcdObjectData(typeId, defaultParams.toMutableList())
    )

    abstract fun wrap(impl: ActorImpl): T
}