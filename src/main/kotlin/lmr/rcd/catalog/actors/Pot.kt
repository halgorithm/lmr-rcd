package lmr.rcd.catalog.actors

import lmr.rcd.models.entity.ActorInterface
import lmr.rcd.catalog.enums.DropType
import lmr.rcd.catalog.enums.Sfx
import lmr.rcd.models.decorators.*
import lmr.rcd.models.entity.ParamSpec
import lmr.rcd.models.entity.Actor

// TODO: `pot: Pot = EntityConvert.convert<Pot>(go)` (except worse because of type erasure? ugh)
class Pot
    @JvmOverloads constructor(
        override val actor: Actor = generateDefaultActor()
    ) : ActorDecorator, ActorInterface by actor {

    enum class Param(
        override val idx: Int,
        override val defaultValue: Short,
        override val validValueRanges: List<IntRange> = listOf(Short.MIN_VALUE..Short.MAX_VALUE)
    ) : ParamSpec {
        DROP_TYPE(0, DropType.NOTHING.value, DropType.valueRanges),
        QUANTITY(1, 0, listOf(0..100)),
        FLAG(2, -1, listOf(-1..2056)),
        FLAG_BIT(3, 1, listOf(1..128)),
        KIND(4, 0, listOf(0..19)), // TODO: Kind.valueRanges and Kind.WHATEVER_DEFAULT.value
        HIT_SOUND(5, 105, Sfx.valueRanges),
        BREAK_SOUND(6, 35, Sfx.valueRanges),
        LAND_SOUND(7, 17, Sfx.valueRanges),
        PITCH_SHIFT(8, 0, listOf(-500..0));
    }

    enum class Kind(override val value: Short) : ParamChoice {
        // Don't know what they are yet
        INVALID(-1);

        companion object : ParamChoiceCompanion<Kind>(
            items = values(), unknownItem = INVALID
        ) {
            @JvmStatic override fun valueOf(value: Short) = super.valueOf(value)
        }
    }

    var dropType by EnumParamAccessor(Param.DROP_TYPE, DropType)
    var quantity by NumberParamAccessor(Param.QUANTITY)
    var flag by NumberParamAccessor(Param.FLAG)
    var flagBit by NumberParamAccessor(Param.FLAG_BIT)
    var kind by EnumParamAccessor(Param.KIND, Kind)
    var hitSound by EnumParamAccessor(Param.HIT_SOUND, Sfx)
    var breakSound by EnumParamAccessor(Param.BREAK_SOUND, Sfx)
    var landSound by EnumParamAccessor(Param.LAND_SOUND, Sfx)
    var pitchShift by NumberParamAccessor(Param.PITCH_SHIFT)

    override fun copy(): Pot = wrap(actor.copy())

    // EntityInfo<Pot>(0x00, Param.defaultParams)
    companion object Static : ActorDecoratorCompanion<Pot, Param>
        (typeId = 0x00, paramSpecs = Param.values())
    {
        const val TYPE_ID: Short = 0x00

        @JvmStatic override fun wrap(actor: Actor): Pot = Pot(actor)
    }
}
