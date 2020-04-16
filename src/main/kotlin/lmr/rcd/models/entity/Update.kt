package lmr.rcd.models.entity

data class Update
    @JvmOverloads constructor(
        var flag: Short,
        var value: Byte,
        var operator: UpdateOperator = UpdateOperator.SET
    )
{
    fun toTerseString(): String =
        "Update(0x${Integer.toHexString(flag.toInt())} $operator $value)"
}