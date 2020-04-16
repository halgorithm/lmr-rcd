package lmr.rcd.models.hierarchy.storage

import lmr.rcd.models.entity.Actor
import java.lang.IllegalArgumentException

class ActorStorageImpl
    @JvmOverloads constructor(actors: List<Actor> = listOf())
: ActorStorage {
    private val actorsByIdByType = mutableMapOf<Short, MutableMap<Int, Actor>>()

    init { actors.forEach(::addActor) }

    override fun addActor(effect: Actor) {
        if (effect.parent != null)
            throw IllegalArgumentException("Actor is already added to ${effect.parent}")

        getActorsMap(effect.typeId)[effect._id] = effect
        effect._attachTo(this)
    }

    override fun removeActor(effect: Actor) {
        if (effect.parent != this)
            throw IllegalArgumentException("Actor is not attached to this object")

        getActorsMap(effect.typeId).remove(effect._id)
        effect._detach()
    }

    override fun fetchOwnActors(): List<Actor> =
            actorsByIdByType.values.flatMap { it.values }

    private fun getActorsMap(typeId: Short): MutableMap<Int, Actor> =
            actorsByIdByType.getOrPut(typeId) { mutableMapOf() }
}