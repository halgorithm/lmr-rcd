package lmr.rcd.models.decorators

import lmr.rcd.util.ValueLookup

open class ParamChoiceCompanion<T : ParamChoice>
    (items: Array<T>, val unknownItem: T)
: ValueLookup<Short, T> {
    private val map = items.associateBy { it.value }
    val valueRanges = calcValueRanges(items.map { it.value })

    override fun valueOf(value: Short) = map.getOrDefault(value, unknownItem)

    companion object {
        fun calcValueRanges(values: List<Short>): Array<IntRange> {
            val sortedValues = values.sorted()
            val res = mutableListOf<IntRange>()

            var prevValue = sortedValues[0]
            var nextRangeStart = prevValue
            for (value in sortedValues.subList(1, sortedValues.size)) {
                if (value == (prevValue + 1).toShort()) {
                    prevValue = value
                    continue
                }

                res.add(nextRangeStart..prevValue)
                nextRangeStart = value
                prevValue = value
            }

            if (prevValue == nextRangeStart) res.add(prevValue..prevValue)

            return res.toTypedArray()
        }
    }
}
