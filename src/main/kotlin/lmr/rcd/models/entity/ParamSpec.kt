package lmr.rcd.models.entity

interface ParamSpec {
    val idx: Int
    val range: IntRange
    val default: Short
}