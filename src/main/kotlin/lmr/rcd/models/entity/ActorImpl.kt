package lmr.rcd.models.entity

class ActorImpl
    @JvmOverloads constructor(
        data: RcdObjectData,
        override var pos: Position = Position()
    )
: EntityImpl(data), Actor {
    override fun copy(): ActorImpl = ActorImpl(data.copy(), pos.copy())
}
