package lmr.rcd.io

import kotlin.math.pow

// Big-endian bit producer and consumer (like a ByteBuffer for bits instead of bytes).
class BitsProcessor(var value: Int = 0) {
    var position: Byte = 0

    // Return the bitsCount bits left of the current position, then increment (move LEFT) the position by bitsCount.
    fun get(bitsCount: Int): Int = get(bitsCount.toByte())
    fun get(bitsCount: Byte): Int {
        val res = get(position, bitsCount)
        advance(bitsCount)
        return res
    }

    fun get(index: Int, bitsCount: Int): Int = get(index.toByte(), bitsCount.toByte())
    fun get(index: Byte, bitsCount: Byte): Int {
        val intIndex = index.toInt()
        val mask = maxValue(bitsCount) shl intIndex
        return (value and mask) ushr intIndex
    }

    fun put(value: Int, bitsCount: Int): BitsProcessor = put(value, bitsCount.toByte())
    fun put(value: Int, bitsCount: Byte): BitsProcessor {
        put(position, value, bitsCount)
        advance(bitsCount)
        return this
    }

    fun put(index: Int, value: Int, bitsCount: Int): BitsProcessor = put(index.toByte(), value, bitsCount.toByte())
    fun put(index: Byte, value: Int, bitsCount: Byte): BitsProcessor {
        val intIndex = index.toInt()
        this.value = this.value and (maxValue(bitsCount) shl intIndex).inv() // zero the bits we are about to write to
        this.value = this.value or (clampToBits(value, bitsCount) shl intIndex) // write the new value
        return this
    }

    fun advance(bitsCount: Int): BitsProcessor = advance(bitsCount.toByte())
    fun advance(bitsCount: Byte): BitsProcessor {
        position = (position + bitsCount).toByte()
        return this
    }

    companion object {
        // returns the maximum int representable within bitsCount bits.
        private fun maxValue(bitsCount: Byte): Int = maxValue(bitsCount.toInt())
        private fun maxValue(bitsCount: Int): Int =
            2.0.pow(bitsCount).toInt() - 1

        // clamps value between 0 and the max value for the provided bitsCount;
        private fun clampToBits(value: Int, bitsCount: Byte): Int =
            value.coerceIn(0, maxValue(bitsCount))
    }
}