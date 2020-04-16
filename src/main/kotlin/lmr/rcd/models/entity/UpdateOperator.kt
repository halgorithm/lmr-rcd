package lmr.rcd.models.entity

import lmr.rcd.util.ValueLookup
import lmr.rcd.util.Valued

enum class UpdateOperator
    (override val value: Byte)
: Valued<Byte> {
    SET(0),
    ADD(1),
    SUB(2),
    MULT(3),
    DIV(4),
    BIT_AND(5),
    BIT_OR(6),
    BIT_XOR(7),
    INVALID(-1);

    companion object: ValueLookup<Byte, UpdateOperator> {
        private val map = values().associateBy(UpdateOperator::value)
        @JvmStatic override fun valueOf(value: Byte) = map.getOrDefault(value, INVALID)
    }
}