package lmr.rcd.models.decorators

import lmr.rcd.models.entity.EntityInterface

abstract class EntityDecorator : EntityInterface {
    abstract fun toDebugString(): String
}