package lmr.rcd.models.entity

import lmr.rcd.models.Flag

data class Test
    @JvmOverloads constructor(
        var flag: Short,
        var value: Byte,
        var operator: TestOperator = TestOperator.EQ
    )
{
    fun toTerseString(): String =
        "${Flag.toFlagString(flag)} ${operator.name} $value"
}