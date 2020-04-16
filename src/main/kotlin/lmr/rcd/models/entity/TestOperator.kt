package lmr.rcd.models.entity

import lmr.rcd.util.ValueLookup
import lmr.rcd.util.Valued

enum class TestOperator
    (override val value: Byte)
: Valued<Byte> {
    EQ(0x00),
    LT_EQ(0x01),
    GT_EQ(0x02),
    BIT_AND_NOT_0(0x03),
    BIT_OR_NOT_0(0x04),
    BIT_XOR_NOT_0(0x05),
    EQ_0(0x06), // bugged in-game to always return true
    NOT_EQ(0x40),
    GT(0x41),
    LT(0x42),
    BIT_AND_EQ_0(0x43),
    BIT_OR_EQ_0(0x44),
    BIT_XOR_EQ_0(0x45), // bugged in-game to always return true
    NOT_0(0x46),
    INVALID(-1);

    companion object: ValueLookup<Byte, TestOperator> {
        private val map = values().associateBy(TestOperator::value)
        @JvmStatic override fun valueOf(value: Byte) = map.getOrDefault(value, INVALID)
    }
}