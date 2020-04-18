package lmr.rcd.models.decorators

import lmr.rcd.models.entity.*
import java.lang.IllegalArgumentException

abstract class EntityDecoratorCompanion<P : ParamSpec>
    (val typeId: Short, paramSpecs: Array<P>)
{
    protected val defaultParams = {
        if (paramSpecs.size != paramSpecs.distinctBy { it.idx }.size)
            throw IllegalArgumentException("param specs indices are not unique")

        ParamSpec.generateDefaultParams(paramSpecs)
    }.invoke()
}