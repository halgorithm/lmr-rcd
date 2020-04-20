package lmr.rcd.catalog.enums

import lmr.rcd.models.decorators.ParamChoice
import lmr.rcd.models.decorators.ParamChoiceCompanion

enum class D3DBlendMode(override val value: Short) : ParamChoice {
    ZERO(0),
    ONE(1),
    DEST_COLOR(2),
    INV_DEST_COLOR(3),
    SRC_ALPHA(4),
    INV_SRC_ALPHA(5),
    DEST_ALPHA(6),
    INV_DEST_ALPHA(7),
    INVALID(-1);

    companion object: ParamChoiceCompanion<D3DBlendMode>(values(), INVALID) {
        @JvmStatic override fun valueOf(value: Short) = super.valueOf(value)
    }
}