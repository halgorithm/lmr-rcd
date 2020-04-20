package lmr.rcd.models.decorators

import lmr.rcd.models.entity.EntityInterface
import lmr.rcd.models.entity.ParamSpec
import kotlin.reflect.KProperty

class BooleanParamAccessor(
    private val paramSpec: ParamSpec,
    private val trueValue: Short = 1,
    private val falseValue: Short = 0
) {
    operator fun getValue(entity: EntityInterface, property: KProperty<*>): Boolean {
        return entity.params[paramSpec.idx] == trueValue
    }

    operator fun setValue(entity: EntityInterface, property: KProperty<*>, value: Boolean) {
        entity.setParam(paramSpec, if (value) trueValue else falseValue)
    }
}