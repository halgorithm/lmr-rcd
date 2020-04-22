package lmr.rcd.models.entity

interface EntityInterface {
    val _id: Int
    val parent: Any?

    var data: RcdObjectData

    val typeId: Short
    val params: MutableList<Short>
    val tests: MutableList<Test>
    val updates: MutableList<Update>

    fun _attachTo(obj: Any)
    fun _detach()

    fun getParam(paramSpec: ParamSpec): Short = params[paramSpec.idx]

    fun setParam(paramSpec: ParamSpec, value: Short) = setParam(paramSpec, value, true)
    fun setParam(paramSpec: ParamSpec, value: Short, validate: Boolean) {
        if (validate) {
            for (range in paramSpec.validValueRanges) {
                if (value !in range)
                    throw IllegalArgumentException(
                        "$value is not a valid value for ${paramSpec.name} (must be in the set ${paramSpec.validValueRanges})"
                    )
            }
        }

        params[paramSpec.idx] = value
    }

    fun copy(): EntityInterface
}