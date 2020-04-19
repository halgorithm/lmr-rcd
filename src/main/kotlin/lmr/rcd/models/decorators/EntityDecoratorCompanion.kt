package lmr.rcd.models.decorators

import lmr.rcd.models.entity.*
import java.lang.IllegalArgumentException

abstract class EntityDecoratorCompanion<P : ParamSpec>
    (val typeId: Short, paramSpecs: Array<P>)
{
    protected val defaultParams = {
        validateParamSpecs(paramSpecs)
        ParamSpec.generateDefaultParams(paramSpecs)
    }.invoke()

    private fun validateParamSpecs(paramSpecs: Array<P>) {
        var prevIdx = paramSpecs.first().idx
        for (i in 1 until paramSpecs.size) {
            val curIdx = paramSpecs[i].idx
            if (curIdx <= prevIdx)
                throw IllegalArgumentException("param specs indices are not in increasing order starting at $curIdx")

            prevIdx = curIdx
        }
    }
}