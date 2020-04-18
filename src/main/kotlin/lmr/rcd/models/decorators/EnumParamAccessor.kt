package lmr.rcd.models.decorators

import lmr.rcd.models.entity.Entity
import lmr.rcd.models.entity.ParamSpec
import kotlin.reflect.KProperty

class EnumParamAccessor<T : ParamChoice>(
    private val paramSpec: ParamSpec,
    private val lookup: ParamChoiceCompanion<T>
) {

    operator fun getValue(entity: Entity, property: KProperty<*>): T {
        val value = entity.getParam(paramSpec)
        return lookup.valueOf(value)
    }

    operator fun setValue(entity: Entity, property: KProperty<*>, choice: T) {
        entity.setParam(paramSpec, choice.value)
    }
}