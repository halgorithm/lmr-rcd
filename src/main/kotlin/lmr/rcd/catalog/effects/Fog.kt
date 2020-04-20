package lmr.rcd.catalog.actors

import lmr.rcd.catalog.enums.D3DBlendMode
import lmr.rcd.models.decorators.*
import lmr.rcd.models.entity.ParamSpec
import lmr.rcd.models.entity.Effect

class Fog
    @JvmOverloads constructor(
        effect: Effect = generateDefaultEffect()
    )
: EffectDecorator(effect) {

    enum class Param(
        override val idx: Int,
        override val defaultValue: Short,
        override val validValueRanges: List<IntRange> = listOf(Short.MIN_VALUE..Short.MAX_VALUE)
    ) : ParamSpec {
        MOVE_ANGLE(0, 0, listOf(0..360)),
        SPEED_CONTROL(1, 0, listOf(0..Short.MAX_VALUE)), // Film grain speed = value % 50, fog speed = value / 100 (units?)
        SRC_ALPHA_RANGE_MAX(2, 255, listOf(0..255)),
        SRC_ALPHA_RANGE_MIN(3, 255, listOf(0..255)),
        FRAMES_PER_ALPHA(4, 1, listOf(0..Short.MAX_VALUE)),
        ALPHA_FADE_STYLE(6, AlphaFadeStyle.INCREASE_THEN_STOP.value, AlphaFadeStyle.valueRanges),
        DEFAULT_BLEND_MODE_VALUE(7, 1, listOf(1..1)),
        SRC_BLEND_MODE(8, D3DBlendMode.ZERO.value, D3DBlendMode.valueRanges),
        DEST_BLEND_MODE(9, D3DBlendMode.ONE.value, D3DBlendMode.valueRanges),
        GFX_IDX(12, 0, listOf(0..1)),
        PATTERN(13, 0, listOf(0..5)),
        SRC_RED(14, 255, listOf(0..255)),
        SRC_GREEN(15, 255, listOf(0..255)),
        SRC_BLUE(16, 255, listOf(0..255)),
        LEMEZA_SPOTLIGHT(17, 0, listOf(0..1))
    }

    enum class AlphaFadeStyle(override val value: Short) : ParamChoice {
        INCREASE_THEN_STOP(0),
        INCREASE_THEN_RESET(1),
        INCREASE_THEN_DECREASE(2),
        INVALID(-1);

        companion object Info : ParamChoiceCompanion<AlphaFadeStyle>(
            items = values(), unknownItem = INVALID
        ) {
            @JvmStatic override fun valueOf(value: Short) = super.valueOf(value)
        }
    }

    var moveAngle by NumberParamAccessor(Param.MOVE_ANGLE)
    var speedControl by NumberParamAccessor(Param.SPEED_CONTROL)
    var srcAlphaRangeMax by NumberParamAccessor(Param.SRC_ALPHA_RANGE_MAX)
    var srcAlphaRangeMin by NumberParamAccessor(Param.SRC_ALPHA_RANGE_MIN)
    var framesPerAlpha by NumberParamAccessor(Param.FRAMES_PER_ALPHA)
    var alphaFadeStyle by EnumParamAccessor(Param.ALPHA_FADE_STYLE, AlphaFadeStyle)
    var defaultBlendModeValue by NumberParamAccessor(Param.DEFAULT_BLEND_MODE_VALUE)
    var srcBlendMode by EnumParamAccessor(Param.SRC_BLEND_MODE, D3DBlendMode)
    var destBlendMode by EnumParamAccessor(Param.DEST_BLEND_MODE, D3DBlendMode)
    var gfxIdx by NumberParamAccessor(Param.GFX_IDX)
    var pattern by NumberParamAccessor(Param.PATTERN)
    var srcRed by NumberParamAccessor(Param.SRC_RED)
    var srcGreen by NumberParamAccessor(Param.SRC_GREEN)
    var srcBlue by NumberParamAccessor(Param.SRC_BLUE)
    var lemezaSpotlight by BooleanParamAccessor(Param.LEMEZA_SPOTLIGHT)

    override fun toDebugString() = toDebugString(TYPE_ID, Param.values())
    override fun copy(): Fog = Fog.wrap(effect.copy())

    companion object Static: EffectDecoratorCompanion<Fog, Param>(
        typeId = 0x92, paramSpecs = Param.values()
    ) {
        const val TYPE_ID: Short = 0x92

        @JvmStatic override fun wrap(effect: Effect): Fog = Fog(effect)
    }
}
