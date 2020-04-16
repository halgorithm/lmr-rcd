package lmr.rcd.util

import lmr.rcd.models.entity.Entity
import lmr.rcd.models.entity.ParamSpec
import kotlin.reflect.KProperty

class ParamAccessor(private val paramSpec: ParamSpec) {
    operator fun getValue(entity: Entity, property: KProperty<*>): Int {
        return entity.params[paramSpec.idx].toInt()
    }

    operator fun setValue(entity: Entity, property: KProperty<*>, value: Int) {
        entity.params[paramSpec.idx] = value.toShort()
    }
}