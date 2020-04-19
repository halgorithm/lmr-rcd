package lmr.rcd.models

object Flag {
    // TODO: do valueOf() and print flag name if present
    fun toFlagString(flag: Short) = "[${"%04x".format(flag)}]"
}