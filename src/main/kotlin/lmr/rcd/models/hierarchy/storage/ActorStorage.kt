package lmr.rcd.models.hierarchy.storage

import lmr.rcd.models.decorators.ActorDecorator
import lmr.rcd.models.entity.Actor
import lmr.rcd.models.entity.ActorInterface
import java.lang.IllegalArgumentException

class ActorStorage
    @JvmOverloads constructor(actors: List<ActorInterface> = listOf())
: ActorStorageInterface {
    private val actorsByIdByType = mutableMapOf<Short, MutableMap<Int, Actor>>()

    init { addActors(actors) }

    override fun addActor(actor: Actor) {
        if (actor.parent != null)
            throw IllegalArgumentException("Actor is already added to ${actor.parent}")

        getActorsMap(actor.typeId)[actor._id] = actor
        actor._attachTo(this)
    }

    override fun removeActor(actor: Actor) {
        if (actor.parent != this)
            throw IllegalArgumentException("Actor is not attached to this object")

        getActorsMap(actor.typeId).remove(actor._id)
        actor._detach()
    }

    override fun fetchOwnActors(): List<Actor> =
            actorsByIdByType.values.flatMap { it.values }

    private fun getActorsMap(typeId: Short): MutableMap<Int, Actor> =
            actorsByIdByType.getOrPut(typeId) { mutableMapOf() }
}