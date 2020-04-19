package lmr.rcd.models.entity

import lmr.rcd.models.Flag

data class Update
    @JvmOverloads constructor(
        var flag: Short,
        var value: Byte,
        var operator: UpdateOperator = UpdateOperator.SET
    )
{
    fun toTerseString(): String =
        "${Flag.toFlagString(flag)} ${operator.name} $value"
}