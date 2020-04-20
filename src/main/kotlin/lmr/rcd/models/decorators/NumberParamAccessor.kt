package lmr.rcd.models.decorators

import lmr.rcd.models.entity.EntityInterface
import lmr.rcd.models.entity.ParamSpec
import kotlin.reflect.KProperty

class NumberParamAccessor(private val paramSpec: ParamSpec) {
    operator fun getValue(entity: EntityInterface, property: KProperty<*>): Int {
        return entity.params[paramSpec.idx].toInt()
    }

    operator fun setValue(entity: EntityInterface, property: KProperty<*>, value: Int) {
        entity.setParam(paramSpec, value.toShort())
    }
}