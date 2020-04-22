package lmr.rcd.schema

import lmr.rcd.models.entity.ParamSpec

data class EntityDecoratorDef(
    val className: String,
    val typeId: Short,
    val isActor: Boolean,
    val paramSpecs: List<ParamSpec>,
    val enumInfoByParamSpecName: Map<String, Pair<String, String>> = mapOf(), // { paramSpecName: (enumName, defaultItemName) }
    val paramChoiceEnumDefs: List<ParamChoiceEnumDef> = listOf()
)