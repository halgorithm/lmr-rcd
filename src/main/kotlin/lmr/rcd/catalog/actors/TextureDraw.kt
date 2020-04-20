package lmr.rcd.catalog.actors

import lmr.rcd.models.decorators.*
import lmr.rcd.models.entity.Actor
import lmr.rcd.models.entity.ParamSpec

class TextureDraw
    @JvmOverloads constructor(
        actor: Actor = generateDefaultActor()
    )
: ActorDecorator(actor) {
    enum class Param(
        override val idx: Int,
        override val defaultValue: Short,
        override val validValueRanges: List<IntRange> = listOf(Short.MIN_VALUE..Short.MAX_VALUE)
    ) : ParamSpec {
        LAYER_IDX(0, 0),
        GFX_SHEET_IDX(1, 0, listOf(0..7)),
        GFX_SHEET_X(2, 0, listOf(0..1024)),
        GFX_SHEET_Y(3, 0, listOf(0..1024)),
        GFX_SHEET_WIDTH(4, 0),
        GFX_SHEET_HEIGHT(5, 0),
        ANIMATION_STYLE(6, AnimationStyle.ANIMATE_AT_START.value, AnimationStyle.valueRanges),
        GFX_SHEET_FRAMES_COUNT(7, 1, listOf(1..1024)),
        PAUSE_INTERVAL(8, 1, listOf(0..Short.MAX_VALUE)),
        REPEAT_COUNT(9, -1, listOf(-1..Short.MAX_VALUE)),
        COLLISION_TYPE_FILL(10, 0, listOf(Byte.MIN_VALUE..Byte.MAX_VALUE)), // TODO: make enum?
        ENTRY_EFFECT(11, EntryEffect.STATIC.value, EntryEffect.valueRanges),
        EXIT_EFFECT(12, ExitEffect.DISAPPEAR_INSTANTLY.value, ExitEffect.valueRanges),
        CYCLE_COLORS(13, 0, listOf(0..1)),
        MIN_ALPHA(14, 255, listOf(0..255)), // FIXME: test
        MAX_ALPHA(15, 255, listOf(0..255)),
        MIN_RED(16, 255, listOf(0..255)), // FIXME: test
        MAX_RED(17, 255, listOf(0..255)),
        MIN_GREEN(18, 255, listOf(0..255)), // FIXME: test
        MAX_GREEN(19, 255, listOf(0..255)),
        MIN_BLUE(20, 255, listOf(0..255)), // FIXME: test
        MAX_BLUE(21, 255, listOf(0..255)),
        BLEND_STYLE(22, BlendStyle.NORMAL.value, BlendStyle.valueRanges),
        NOT_0(23, 1, listOf(1..Short.MAX_VALUE))
    }

    enum class AnimationStyle(override val value: Short) : ParamChoice {
        SKIP_TO_END(0),
        ANIMATE_AT_START(1),
        UNKNOWN(2),
        INVALID(-1);

        companion object : ParamChoiceCompanion<AnimationStyle>(
            items = values(), unknownItem = INVALID
        ) {
            @JvmStatic
            override fun valueOf(value: Short) = super.valueOf(value)
        }
    }

    enum class EntryEffect(override val value: Short) : ParamChoice {
        STATIC(0),
        FADE(1),
        ANIMATE_SHOW_LAST_FRAME(2),
        INVALID(-1);

        companion object : ParamChoiceCompanion<EntryEffect>(
            items = values(), unknownItem = INVALID
        ) {
            @JvmStatic
            override fun valueOf(value: Short) = super.valueOf(value)
        }
    }

    enum class ExitEffect(override val value: Short) : ParamChoice {
        NO_ANIMATION(0),
        FADE(1),
        DISAPPEAR_INSTANTLY(2),
        SHATTER(3),
        ANIMATE_ON_FAILURE_FIRST_FRAME_ON_SUCCESS(5),
        BREAK_GLASS(6);

        companion object : ParamChoiceCompanion<ExitEffect>(
            items = values(), unknownItem = DISAPPEAR_INSTANTLY
        ) {
            @JvmStatic
            override fun valueOf(value: Short) = super.valueOf(value)
        }
    }

    enum class BlendStyle(override val value: Short) : ParamChoice {
        NORMAL(0),
        ADD(1),

        // don't know the others
        INVALID(-1);

        companion object : ParamChoiceCompanion<BlendStyle>(
            items = values(), unknownItem = NORMAL
        ) {
            @JvmStatic
            override fun valueOf(value: Short) = super.valueOf(value)
        }
    }

    var layerIdx by NumberParamAccessor(Param.LAYER_IDX)
    var gfxSheetIdx by NumberParamAccessor(Param.GFX_SHEET_IDX)
    var gfxSheetX by NumberParamAccessor(Param.GFX_SHEET_X)
    var gfxSheetY by NumberParamAccessor(Param.GFX_SHEET_Y)
    var gfxSheetWidth by NumberParamAccessor(Param.GFX_SHEET_WIDTH)
    var gfxSheetHeight by NumberParamAccessor(Param.GFX_SHEET_HEIGHT)
    var animationStyle by EnumParamAccessor(Param.ANIMATION_STYLE, AnimationStyle)
    var sheetFramesCount by NumberParamAccessor(Param.GFX_SHEET_FRAMES_COUNT)
    var pauseInterval by NumberParamAccessor(Param.PAUSE_INTERVAL)
    var repeatCount by NumberParamAccessor(Param.REPEAT_COUNT)
    var collisionTypeFill by NumberParamAccessor(Param.COLLISION_TYPE_FILL)
    var entryEffect by EnumParamAccessor(Param.ENTRY_EFFECT, EntryEffect)
    var exitEffect by EnumParamAccessor(Param.EXIT_EFFECT, ExitEffect)
    var cycleColors by BooleanParamAccessor(Param.CYCLE_COLORS)
    var minAlpha by NumberParamAccessor(Param.MIN_ALPHA)
    var maxAlpha by NumberParamAccessor(Param.MAX_ALPHA)
    var minRed by NumberParamAccessor(Param.MIN_RED)
    var maxRed by NumberParamAccessor(Param.MAX_RED)
    var minGreen by NumberParamAccessor(Param.MIN_GREEN)
    var maxGreen by NumberParamAccessor(Param.MAX_GREEN)
    var minBlue by NumberParamAccessor(Param.MIN_BLUE)
    var maxBlue by NumberParamAccessor(Param.MAX_BLUE)
    var blendStyle by EnumParamAccessor(Param.BLEND_STYLE, BlendStyle)
    var not0 by NumberParamAccessor(Param.NOT_0)

    override fun toDebugString() = toDebugString(TYPE_ID, Param.values())
    override fun copy(): TextureDraw = wrap(actor.copy())

    companion object Static : ActorDecoratorCompanion<TextureDraw, Param>
        (typeId = 0x93, paramSpecs = Param.values())
    {
        const val TYPE_ID: Short = 0x93

        // FIXME: reject actor if typeId mismatch
        @JvmStatic
        override fun wrap(actor: Actor) = TextureDraw(actor)

    }
}