package lmr.rcd.models.entity

data class Test
    @JvmOverloads constructor(
        var flag: Short,
        var value: Byte,
        var operator: TestOperator = TestOperator.EQ
    )
{
    fun toTerseString(): String =
        "0x${Integer.toHexString(flag.toInt())} ${operator.name} $value"
}