package lmr.rcd.models.decorators

import lmr.rcd.models.entity.ActorInterface
import lmr.rcd.models.entity.Actor

interface ActorDecorator : ActorInterface {
    val actor: Actor
}