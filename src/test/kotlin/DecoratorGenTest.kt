import lmr.rcd.models.entity.ParamSpec
import lmr.rcd.models.entity.ParamSpec.ParamType.*
import lmr.rcd.schema.DecoratorSrcFileGenerator
import lmr.rcd.schema.EntityDecoratorDef
import lmr.rcd.schema.ParamChoiceEnumDef
import java.nio.file.Path

object DecoratorGenTest {
    @JvmStatic fun main(args: Array<String>) {
        // FIXME: parse rcdwype
        // FIXME: move to a task

        val potDef = EntityDecoratorDef(
            "GenTestPot",
            0x00,
            true,
            listOf(
                object : ParamSpec {
                    override val name = "DROP_TYPE"
                    override val idx = 0
                    override val defaultValue = (-100).toShort()
                    override val validValueRanges = listOf(-100..-100)
                    override val type = ENUM
                }, object : ParamSpec {
                    override val name = "QUANTITY"
                    override val idx = 1
                    override val defaultValue = 0.toShort()
                    override val validValueRanges = listOf(0..100)
                    override val type = NUMBER
                }
            ),
            mapOf(
                "DROP_TYPE" to Pair("DropType", "NOTHING")
            ), listOf(
                ParamChoiceEnumDef("Kind", mapOf(
                    "GUIDANCE" to 0.toShort(),
                    "INVALID" to (-1).toShort()
                ), "INVALID")
            )
        )

        val decoratorsDirPath = Path.of("E:\\Projects\\lmr-rcd\\src\\main\\kotlin\\generated_decorators")
        DecoratorSrcFileGenerator(potDef).generateToDir(decoratorsDirPath)
        println("Decorators have been generated into $decoratorsDirPath")
    }
}