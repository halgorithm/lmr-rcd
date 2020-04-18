package lmr.rcd.models.decorators

import lmr.rcd.models.entity.Actor
import lmr.rcd.models.entity.ActorImpl

interface ActorDecorator : Actor {
    val impl: ActorImpl
}