package lmr.rcd.catalog.enums

import lmr.rcd.models.decorators.ParamChoice
import lmr.rcd.models.decorators.ParamChoiceCompanion

enum class DropType(override val value: Short) : ParamChoice {
    NOTHING(0),
    COINS(1),
    WEIGHT(2),
    SHURIKENS(3),
    ROLLING_SHURIKENS(4),
    EARTH_SPEARS(5),
    FLARES(6),
    BOMBS(7),
    CHAKRAMS(8),
    CALTROPS(9),
    BULLETS(10),
    COINS_OR_WEIGHT(11), // Only works for enemies
    INVALID(-1);

    companion object : ParamChoiceCompanion<DropType>
        (values(), INVALID)
    {
        @JvmStatic override fun valueOf(value: Short) = super.valueOf(value)
    }
}