package lmr.rcd.models.decorators

import lmr.rcd.models.entity.EntityInterface
import lmr.rcd.models.entity.ParamSpec
import kotlin.reflect.KProperty

class BooleanParamAccessor(
    private val paramSpec: ParamSpec,
    private val trueValueRanges: List<IntRange> = listOf(1..1),
    private val falseValueRanges: List<IntRange> = listOf(0..0)
) {
    operator fun getValue(entity: EntityInterface, property: KProperty<*>): Boolean? {
        val value = entity.params[paramSpec.idx]

        for (range in trueValueRanges)
            if (value in range) return true
        for (range in falseValueRanges)
            if (value in range) return false
        return null
    }

    operator fun setValue(entity: EntityInterface, property: KProperty<*>, value: Boolean?) {
        if (value == null)
            throw IllegalArgumentException("value cannot be null")

        val storedValue =
            if (value) trueValueRanges.first().first
            else falseValueRanges.first().first

        entity.setParam(paramSpec, storedValue.toShort())
    }
}