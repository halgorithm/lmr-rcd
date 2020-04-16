package lmr.rcd.models.hierarchy.storage

import lmr.rcd.models.entity.Actor

interface ActorStorage {
    fun addActor(effect: Actor)
    fun removeActor(effect: Actor)
    fun fetchOwnActors(): List<Actor>
}