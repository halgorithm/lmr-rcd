package lmr.rcd.catalog.enums

import lmr.rcd.models.decorators.ParamChoice
import lmr.rcd.models.decorators.ParamChoiceCompanion

enum class BlendMode(override val value: Short) : ParamChoice {
    EMPTY(0),
    NORMAL(1),
    ADD(2),
    MULTIPLY(3),
    INVALID(-1);

    companion object: ParamChoiceCompanion<BlendMode>(values(), INVALID) {
        @JvmStatic override fun valueOf(value: Short) = super.valueOf(value)
    }
}