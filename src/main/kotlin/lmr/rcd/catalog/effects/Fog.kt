package lmr.rcd.catalog.actors

import lmr.rcd.catalog.enums.BlendMode
import lmr.rcd.catalog.enums.DropType
import lmr.rcd.catalog.enums.Sfx
import lmr.rcd.models.decorators.*
import lmr.rcd.models.entity.*

class Fog
    @JvmOverloads constructor(
        override val impl: EffectImpl = generateDefaultImpl()
    )
: EffectDecorator, Effect by impl {

    enum class Param(
        override val idx: Int, override val validValueRanges: Array<IntRange>, override val defaultValue: Short
    ) : ParamSpec {
        MOVE_DIRECTION(0, arrayOf(-32768..32767), 0),
        FILM_GRAIN_SPEED(1, arrayOf(0..32767), 0),
        MAX_ALPHA(2, arrayOf(0..255), 255),
        MIN_ALPHA(3, arrayOf(0..255), 255),
        FRAMES_PER_ALPHA(4, arrayOf(0..32767), 1),
        ALPHA_FADE_CONTROL(6, AlphaFadeControl.valueRanges, AlphaFadeControl.INCREASE_THEN_STOP.value),
        DEFAULT_BLEND_MODE(7, BlendMode.valueRanges, BlendMode.NORMAL.value),
        SRC_BLEND_MODE(8, BlendMode.valueRanges, BlendMode.NORMAL.value),
    }

    enum class AlphaFadeControl(override val value: Short) : ParamChoice {
        INCREASE_THEN_STOP(0),
        INCREASE_THEN_RESET(1),
        INCREASE_THEN_DECREASE(2),
        INVALID(-1);

        companion object Info : ParamChoiceCompanion<AlphaFadeControl>(
            items = values(), unknownItem = INVALID
        ) {
            @JvmStatic override fun valueOf(value: Short) = super.valueOf(value)
        }
    }

//    var dropType by EnumParamAccessor(Param.DROP_TYPE, DropType)
//    var quantity by ParamAccessor(Param.QUANTITY)
//    var flag by ParamAccessor(Param.FLAG)
//    var flagBit by ParamAccessor(Param.FLAG_BIT)
//    var kind by EnumParamAccessor(Param.KIND, Kind)
//    var hitSound by EnumParamAccessor(Param.HIT_SOUND, Sfx)
//    var breakSound by EnumParamAccessor(Param.BREAK_SOUND, Sfx)
//    var landSound by EnumParamAccessor(Param.LAND_SOUND, Sfx)
//    var pitchShift by ParamAccessor(Param.PITCH_SHIFT)

    override fun copy(): Fog = Fog.wrap(impl.copy())

    companion object Static: EffectDecoratorCompanion<Fog, Param>(
        typeId = 0x92, paramSpecs = Param.values()
    ) {
        const val TYPE_ID: Short = 0x00

        @JvmStatic override fun wrap(impl: EffectImpl): Fog = Fog(impl)
    }
}
