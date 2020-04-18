package lmr.rcd.catalog.actors

import lmr.rcd.models.entity.Actor
import lmr.rcd.catalog.enums.DropType
import lmr.rcd.catalog.enums.Sfx
import lmr.rcd.models.decorators.*
import lmr.rcd.models.entity.ParamSpec
import lmr.rcd.models.entity.ActorImpl

// TODO: `pot: Pot = EntityConvert.convert<Pot>(go)` (except worse because of type erasure? ugh)
class Pot
    @JvmOverloads constructor(
        override val impl: ActorImpl = generateDefaultImpl()
    ) : ActorDecorator, Actor by impl {

    enum class Param(
        override val idx: Int, override val validValueRanges: Array<IntRange>, override val defaultValue: Short
    ) : ParamSpec {
        DROP_TYPE(0, DropType.valueRanges, DropType.NOTHING.value),
        QUANTITY(1, arrayOf(0..100), 0),
        FLAG(2, arrayOf(-1..2056), -1),
        FLAG_BIT(3, arrayOf(1..128), 1),
        KIND(4, arrayOf(0..19), 0), // TODO: Kind.valueRanges and Kind.WHATEVER_DEFAULT.value
        HIT_SOUND(5, Sfx.valueRanges, 105),
        BREAK_SOUND(6, Sfx.valueRanges, 35),
        LAND_SOUND(7, Sfx.valueRanges, 17),
        PITCH_SHIFT(8, arrayOf(-500..0), 0);
    }

    enum class Kind(override val value: Short) : ParamChoice {
        // Don't know what they are yet
        INVALID(-1);

        companion object : ParamChoiceCompanion<Kind>(
            values(), INVALID
        ) {
            @JvmStatic override fun valueOf(value: Short) = super.valueOf(value)
        }
    }

    var dropType by EnumParamAccessor(Param.DROP_TYPE, DropType)
    var quantity by ParamAccessor(Param.QUANTITY)
    var flag by ParamAccessor(Param.FLAG)
    var flagBit by ParamAccessor(Param.FLAG_BIT)
    var kind by EnumParamAccessor(Param.KIND, Kind)
    var hitSound by EnumParamAccessor(Param.HIT_SOUND, Sfx)
    var breakSound by EnumParamAccessor(Param.BREAK_SOUND, Sfx)
    var landSound by EnumParamAccessor(Param.LAND_SOUND, Sfx)
    var pitchShift by ParamAccessor(Param.PITCH_SHIFT)

    override fun copy(): Pot = wrap(impl.copy())

    // EntityInfo<Pot>(0x00, Param.defaultParams)
    companion object Static : ActorDecoratorCompanion<Pot, Param>
        (typeId = 0x00, paramSpecs = Param.values())
    {
        const val TYPE_ID: Short = 0x00

        @JvmStatic override fun wrap(impl: ActorImpl): Pot = Pot(impl)
    }
}
