package lmr.rcd.models.decorators

import lmr.rcd.models.entity.Entity
import lmr.rcd.models.entity.ParamSpec
import kotlin.reflect.KProperty

class BooleanParamAccessor(
    private val paramSpec: ParamSpec,
    private val trueValue: Short = 1,
    private val falseValue: Short = 0
) {
    operator fun getValue(entity: Entity, property: KProperty<*>): Boolean {
        return entity.params[paramSpec.idx] == trueValue
    }

    operator fun setValue(entity: Entity, property: KProperty<*>, value: Boolean) {
        entity.params[paramSpec.idx] = if (value) trueValue else falseValue
    }
}