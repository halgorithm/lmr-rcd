package lmr.rcd.models.entity

interface ParamSpec {
    val name: String
    val idx: Int
    val validValueRanges: List<IntRange>
    val defaultValue: Short

    companion object {
        fun <T : ParamSpec> generateDefaultParams(specs: Array<T>): List<Short> {
            val paramsLength = specs.maxBy { it.idx }!!.idx

            val res = MutableList<Short>(paramsLength) { 0 }
            specs.forEach { res[it.idx] = it.defaultValue }

            return res
        }
    }
}