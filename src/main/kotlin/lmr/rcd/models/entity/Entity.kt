package lmr.rcd.models.entity

interface Entity {
    val _id: Int
    val parent: Any?

    var data: RcdObjectData

    var typeId: Short
    val params: MutableList<Short>
    val tests: MutableList<Test>
    val updates: MutableList<Update>

    fun _attachTo(obj: Any)
    fun _detach()

    fun getParam(paramSpec: ParamSpec): Short = params[paramSpec.idx]

    fun setParam(paramSpec: ParamSpec, value: Short) {
        if (value !in paramSpec.range)
            throw IllegalArgumentException("must be in range ${paramSpec.range}")

        params[paramSpec.idx] = value
    }

    fun copy(): Entity
}