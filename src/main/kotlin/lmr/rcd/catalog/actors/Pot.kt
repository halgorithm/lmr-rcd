package lmr.rcd.catalog.actors

import lmr.rcd.models.entity.Actor
import lmr.rcd.catalog.enums.DropType
import lmr.rcd.catalog.enums.Sfx
import lmr.rcd.models.entity.ParamSpec
import lmr.rcd.models.entity.ActorImpl
import lmr.rcd.models.entity.RcdObjectData
import lmr.rcd.util.*

// TODO: `pot: Pot = GameObjectConvert.convert<Pot>(go)` (except worse because of type erasure? ugh)
class Pot
    @JvmOverloads constructor(
        actorImpl: ActorImpl = generateActorImpl()
    ) : Actor by actorImpl {

    enum class Param(
        override val idx: Int, override val range: IntRange, override val default: Short
    ) : ParamSpec {
        DROP_TYPE(0, 0..DropType.values().size, DropType.NOTHING.value),
        QUANTITY(1, 0..100, 0),
        FLAG(2, -1..2056, -1),
        FLAG_BIT(3, 1..128, 1),
        KIND(4, 0..19, 0),
        HIT_SOUND(5, 105..105, 105),
        BREAK_SOUND(6, 35..100, 35),
        LAND_SOUND(7, 17..17, 17),
        PITCH_SHIFT(8, -500..0, 0);

        internal companion object Static {
            @JvmStatic fun generateDefaults(): MutableList<Short> =
                values().map { it.default }.toMutableList()
        }
    }

    enum class Kind(override val value: Short) : ParamChoice {
        // Don't know what they are yet
        INVALID(-1);

        internal companion object Static: ParamLookup<Kind> {
            private val map = values().associateBy(Kind::value)
            @JvmStatic override fun valueOf(value: Short) = map.getOrDefault(value, INVALID)
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

    internal companion object Static: EntityInfo {
        const val TYPE_ID: Short = 0x00
        override val typeId = TYPE_ID

        @JvmStatic fun wrap(actor: Actor): Pot = Pot(actor.impl)

        @JvmStatic fun generateActorImpl(): ActorImpl =
            ActorImpl(
                RcdObjectData(typeId, Param.generateDefaults())
            )
    }
}
