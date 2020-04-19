package lmr.rcd.models.decorators

import lmr.rcd.models.entity.*

class EntityStringifier {
    companion object Static {
        fun <P: ParamSpec> stringifyEffect(effect: Effect, typeName: String, expectedTypeId: Short, paramSpecs: Array<P>): String =
            stringifyBase(effect, typeName, expectedTypeId, paramSpecs)

        fun <P: ParamSpec> stringifyActor(actor: Actor, typeName: String, expectedTypeId: Short, paramSpecs: Array<P>) =
            stringifyBase(actor, typeName, expectedTypeId, paramSpecs, actor.pos)

        fun <P: ParamSpec> stringifyBase(entity: Entity, typeName: String, expectedTypeId: Short, paramSpecs: Array<P>, pos: Position? = null): String {
            // TODO: print flag names wherever flags appear
            // TODO: make param values more readable for enum properties

            val typeIdStr = RcdObjectData.toTypeIdStr(entity.typeId)
            val name = if (entity.typeId == expectedTypeId) typeName else "WRONG TYPE ID for $typeName"
            val header = "${typeIdStr} $name (${entity._id})"
            val posText = if (pos == null) "EFFECT" else "${pos.x}, ${pos.y}"
            val testsContents = entity.tests.joinToString("\n") { it.toTerseString() }.ifEmpty { "none" }
            val updatesContents = entity.updates.joinToString("\n") { it.toTerseString() }.ifEmpty { "none" }
            val paramsContents = paramSpecs.joinToString("\n") {
                val valueStr =
                    if (it.idx < entity.params.size) ": ${entity.getParam(it)}"
                    else " IS MISSING!"
                "${it.idx} ${it.name}${valueStr}"
            }

            return """
$header
  $posText
  Tests
${testsContents.prependIndent("    ")}
  Updates
${updatesContents.prependIndent("    ")}
  Params
${paramsContents.prependIndent("    ")}
            """.trimIndent()
        }
    }
}