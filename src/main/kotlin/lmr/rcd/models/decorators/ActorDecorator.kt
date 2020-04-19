package lmr.rcd.models.decorators

import lmr.rcd.models.entity.ActorInterface
import lmr.rcd.models.entity.Actor
import lmr.rcd.models.entity.ParamSpec

abstract class ActorDecorator
    (val actor: Actor)
: EntityDecorator(), ActorInterface by actor {
    protected fun <P: ParamSpec> toDebugString(expectedTypeId: Short, paramSpecs: Array<P>): String =
        EntityStringifier.stringifyActor(actor, this::class.simpleName!!, expectedTypeId, paramSpecs)
}