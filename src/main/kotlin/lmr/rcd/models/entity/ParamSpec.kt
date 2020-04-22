package lmr.rcd.models.entity

interface ParamSpec {
    val name: String
    val idx: Int
    val defaultValue: Short
    val validValueRanges: List<IntRange>
    val type: ParamType

    enum class ParamType { NUMBER, ENUM, BOOLEAN }

    companion object {
        fun <T : ParamSpec> generateDefaultParams(specs: Array<T>): List<Short> {
            val paramsLength = specs.maxBy { it.idx }!!.idx + 1

            val res = MutableList<Short>(paramsLength) { 0 }
            specs.forEach { res[it.idx] = it.defaultValue }

            return res
        }
    }
}