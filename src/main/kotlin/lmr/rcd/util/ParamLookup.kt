package lmr.rcd.util

interface ParamLookup<T : ParamChoice>
    : ValueLookup<Short, T>