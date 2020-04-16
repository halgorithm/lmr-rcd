package lmr.rcd.models.entity

data class Position(val x: Short, val y: Short) {
    constructor() : this(0, 0)
    constructor(x: Int, y: Int) : this(x.toShort(), y.toShort())
}