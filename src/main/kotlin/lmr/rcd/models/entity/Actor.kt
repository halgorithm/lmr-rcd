package lmr.rcd.models.entity

interface Actor : Entity {
    var pos: Position

    fun x() = pos.x
    fun y() = pos.y
    override fun copy(): Actor
}