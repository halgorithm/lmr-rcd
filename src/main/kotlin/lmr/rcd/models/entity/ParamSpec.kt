package lmr.rcd.models.entity

interface ParamSpec {
    val name: String
    val idx: Int
    val validValueRanges: Array<IntRange>
    val defaultValue: Short

    companion object {
        fun <T : ParamSpec> generateDefaultParams(specs: Array<T>): Array<Short> {
            val paramsLength = specs.maxBy { it.idx }!!.idx

            val res = Array<Short>(paramsLength) { 0 }
            specs.forEach { res[it.idx] = it.defaultValue }

            return res
        }
    }
}