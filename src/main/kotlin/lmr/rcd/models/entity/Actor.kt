package lmr.rcd.models.entity

class Actor
    @JvmOverloads constructor(
        data: RcdObjectData,
        override var pos: Position = Position()
    )
: Entity(data), ActorInterface {
    override fun copy(): Actor = Actor(data.copy(), pos.copy())
}
