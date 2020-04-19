package lmr.rcd.models.entity

abstract class Entity
    constructor(override var data: RcdObjectData)
: EntityInterface {
    override val _id = generateId() // REVIEW: does this id get cached?
    override var parent: Any? = null

    override var typeId
        get() = data.typeId
        set(value) { data.typeId = value }

    override val params get() = data.params
    override val tests get() = data.tests
    override val updates get() = data.updates

    override fun _attachTo(obj: Any) { parent = obj }
    override fun _detach() { parent = null }

    private companion object {
        private var nextId = 0;
        fun generateId(): Int = nextId++
    }
}