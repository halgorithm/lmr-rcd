package lmr.rcd.models.hierarchy.storage

import lmr.rcd.models.decorators.ActorDecorator
import lmr.rcd.models.entity.Actor
import lmr.rcd.models.entity.ActorInterface

interface ActorStorageInterface {
    fun fetchOwnActors(): List<Actor>

    fun addActor(actor: Actor)
    fun addActor(decorator: ActorDecorator) = addActor(decorator.actor)
    fun removeActor(actor: Actor)
    fun removeActor(decorator: ActorDecorator) = removeActor(decorator.actor)

    fun addActors(actors: List<ActorInterface>) {
        actors.forEach {
            if (it is ActorDecorator) addActor(it.actor)
            else addActor(it as Actor)
        }
    }

    fun removeActors(actors: List<ActorInterface>) {
        actors.forEach {
            if (it is ActorDecorator) removeActor(it.actor)
            else removeActor(it as Actor)
        }
    }
}