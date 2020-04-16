package lmr.rcd.util

interface ValueLookup<T, L : Valued<T>> {
    fun valueOf(value: T): L
}