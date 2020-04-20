package lmr.rcd.models.decorators

import lmr.rcd.models.entity.*
import java.lang.Integer.max

class EntityStringifier {
    companion object Static {
        fun <P: ParamSpec> stringifyEffect(effect: Effect, typeName: String, expectedTypeId: Short, paramSpecs: Array<P>): String =
            stringifyBase(effect, typeName, expectedTypeId, paramSpecs)

        fun <P: ParamSpec> stringifyActor(actor: Actor, typeName: String, expectedTypeId: Short, paramSpecs: Array<P>) =
            stringifyBase(actor, typeName, expectedTypeId, paramSpecs, actor.pos)

        fun <P: ParamSpec> stringifyBase(entity: Entity, typeName: String, expectedTypeId: Short, paramSpecs: Array<P>, pos: Position? = null): String {
            // TODO: print flag names wherever flags appear

            val typeIdStr = RcdObjectData.toTypeIdStr(expectedTypeId)
            var typeHeader = "$typeIdStr|$typeName${if (pos != null) " (${pos.x}, ${pos.y})" else ""}"
            if (entity.typeId != expectedTypeId)
                typeHeader += "\n  WARNING: WRONG ID ${RcdObjectData.toTypeIdStr(entity.typeId)}"
            val testsContents = entity.tests.joinToString("\n") { it.toTerseString() }.ifEmpty { "none" }
            val updatesContents = entity.updates.joinToString("\n") { it.toTerseString() }.ifEmpty { "none" }
            val paramsContents = stringifyParams(entity.params, paramSpecs)

            return """
$typeHeader
  Tests
${testsContents.prependIndent("    ")}
  Updates
${updatesContents.prependIndent("    ")}
  Params
${paramsContents.prependIndent("    ")}
            """.trimIndent()
        }

        private fun <P: ParamSpec> stringifyParams(params: List<Short>, paramSpecs: Array<P>): String {
            // TODO: make param values more readable for enum properties
            val lastKnownIdx = paramSpecs.last().idx
            val contentsSize = max(params.size, lastKnownIdx + 1)

            var specI = 0
            return MutableList(contentsSize) { i ->
                val paramSpec = paramSpecs.getOrNull(specI)
                val paramValue = params.getOrNull(i)

                val paramName =
                    if (paramSpec == null)
                        "EXCESS"
                    else if (paramSpec.idx != i) {
                        "UNUSED"
                    } else {
                        specI += 1
                        paramSpec.name
                    }

                val content = "$paramName${if (paramValue == null) " IS MISSING!" else ": $paramValue"}"

                "$i $content"
            }.joinToString("\n")
        }
    }
}