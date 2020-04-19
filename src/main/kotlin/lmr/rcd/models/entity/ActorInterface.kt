package lmr.rcd.models.entity

interface ActorInterface : EntityInterface {
    var pos: Position

    fun x() = pos.x
    fun y() = pos.y
    override fun copy(): ActorInterface
}