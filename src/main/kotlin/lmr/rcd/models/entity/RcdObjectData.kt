package lmr.rcd.models.entity

data class RcdObjectData
    @JvmOverloads constructor(
        var typeId: Short,
        val params: MutableList<Short> = mutableListOf(),
        val tests: MutableList<Test> = mutableListOf(),
        val updates: MutableList<Update> = mutableListOf()
    )
{
    companion object Static {
        @JvmStatic fun toTypeIdStr(typeId: Short): String = "%02x".format(typeId)
    }
}