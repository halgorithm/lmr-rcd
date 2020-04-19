package lmr.rcd.models.entity

data class Update
    @JvmOverloads constructor(
        var flag: Short,
        var value: Byte,
        var operator: UpdateOperator = UpdateOperator.SET
    )
{
    fun toTerseString(): String =
        "0x${Integer.toHexString(flag.toInt())} ${operator.name} $value"
}