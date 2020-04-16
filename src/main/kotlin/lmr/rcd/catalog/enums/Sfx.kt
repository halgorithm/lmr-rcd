package lmr.rcd.catalog.enums

import lmr.rcd.util.ParamChoice
import lmr.rcd.util.ParamLookup

enum class Sfx(override val value: Short) : ParamChoice {
    GAME_START(0x00),
    WIND(0x0a),
    ONE_WAY_DOOR_CLOSE(0x0b),
    WATER_ENTER(0x0c),
    // 0x0d ???
    ANKH_ACTIVATE(0x0e),
    ITEM_USE(0x0f),
    // 0x01 throw shuriken?
    // 0x1a throw something
    HP_CRITICAL(0x1b),
    BIRD_FLY(0x1c),
    BIRD_CHIRP(0x1D),
    PUZZLE_SOLVED(0x1e),
    // 0x1f one-way door open... again?
    WHIP_LEATHER(0x02),
    // 0x2a boulder?
    GHOST_APPEAR(0x2b),
    WALL_CRACK(0x2c),
    // 0x2D hand scanner beep thing?
    // 0x2e some kinda slam
    // 0x2f flare explode?
    // 0x03 woosh...
    MENU_SWITCH_TAB(0x3a),
    // 0x3b katana swing?
    // 0x3c water kersploosh, where used again?
    PROJECTILE_FIRE(0x3d),
    // 0x3e collect soul orb?
    DOOR_ENTER(0x3f),
    JUMP_LAND(0x04),
    // 0x4a water swim? liquid damage?
    // 0x4b TEXT_1 (xelpud only?)
    // 0x4c TEXT_2 (nebur only?)
    // 0x4d TEXT_3 (?)
    // 0x4e MUDMAN_SPAWN
    ROLLING_SHURIKEN_ROLL(0x4f),
    LEMEZA_HURT(0x05),
    SIREN_SING(0x5a),
    SHOP_ERROR(0x5b), // or other errors?
    // 0x5c some distant rumble
    // 0x5d flooding/draining water
    RUIN_CRUSHER(0x5e),
    // 0x5f rusty moving metal
    SHIELD_BLOCK(0x06),
    LEMEZA_FACEPLANT(0x6A),
    // 0x6b hand scanner text?
    SHOP_PURCHASE(0x6c),
    COIN_PICKUP(0x6d),
    GLASS_BREAK(0x6e),
    // 0x6f some enemy spawn? warp?
    ENEMY_INVULN(0x07),
    SOFTWARE_COMBO(0x7a),
    // 0x7b uh... blanking here
    SNAKE_HISS(0x7c), // asps? hammerheads? both?
    // 0x7d forget...
    // 0x7e strong boss beam projectile, mother blue pillar
    // 0x7f some kinda laser projectile?
    ENEMY_KILLED(0x08),
    MINIBOSS_DEATH(0x8a), // test
    // 0x8b door close?
    SHIELD_EQUIP(0x8c),
    WOLF_AGGRO(0x8d), // test, only when commanded by rider guys?
    // 0x8e Palenque's mini flying laser bot attacks?
    QUICK_SAVE(0x8f),
    LAVA_ROCK(0x09), // test
    // 0x9a HEALTH_SAP ?
    TORUDE_SCAN(0x9b),
    // 0x9c some kinda ghost thing
    MAUS_PUNCH(0x9d), // rename?
    HEDGEHOG_ROLL(0x9e),
    // 0x9f some kinda enemy jump land attack
    // 0x10 something closing
    // 0x11 trapdoor open? close? both?
    MENU_CURSOR_MOVE(0x12),
    // 0x13 ENEMY_HURT?
    GUARDIAN_KILLED(0x14),
    // 0x15 SHURIKEN_HIT ?
    SKELETON_COLLAPSE(0x16),
    AMMO_PICKUP(0x17),
    EARTH_SPEAR(0x18),
    GRAPPLE(0x19),
    GHOST_KILLED(0x20),
    MSX_OPEN(0x21),
    EMAIL_RECEIVED(0x22),
    POT_BREAK(0x23),
    // 0x24 tog spawn?
    // 0x25 slower menu tab switch? unused?
    HAND_SCAN_START(0x26),
    ITEM_RECEIVED(0x27),
    GUARDIAN_EYE_LEAVE(0x28),
    BLOCK_PUSH(0x29),
    BACKSIDE_DOOR_REVEAL(0x30),
    DIVINE_LIGHTNING(0x31),
    FLARE_FIRE(0x32),
    // 0X33 some kinda projectile
    // 0x34 some other kinda projectile
    // 0x35 something landing?
    BOMB_EXPLODE(0x36),
    // 0x37 waterfall water ambience?
    // 0x38 rushing water ambience
    WEAPON_SWITCH(0x39),
    PISTOL_SHOOT(0x40),
    AXE_SWING(0x41),
    // 0x42 chain or flail whip swing part
    // 0x43 chain or flail whip swing crack
    // 0x44 thump, maaaaaybe a faceplant variant but I don't think so?
    COIN_BOUNCE(0x45),
    GUILD_ALERT(0x46),
    TURRET_SHOOT_1(0x47),
    // 0x48 OBJECT_APPEAR ?
    // 0x49 DRAGON_ENEMY_TELEPORT?
    PUZZLE_FAILED(0x50),
    WALL_WARP(0x51),
    HP_FULL_HEAL(0x52),
    WALL_BREAK(0x53),
    // 0x54 projectile destroy? with an attack?
    // 0x55 chest explosion?
    TOG_KILLED(0x56),
    ELLMAC_ROAR(0x57),
    SHIELD_BREAK(0x58),
    LIQUID_DAMAGE(0x59),
    // 0x60 nebra sky disc rotate?
    FAIRY_VANISH(0x61),
    // FAIRY_HEAL(0x62) fairy fly? fairy follow?,
    // 0x63 water drip? something falls in the water?
    SKELETON_AWAKE(0x64),
    KEY_SWORD(0x65),
    // 0x66 ???
    CHAKRAM_FLY(0x67),
    SHURIKEN_TINK(0x68), // also rolling?
    POT_HIT(0x69),
    // 0x70 ???????
    // 0x71 some boss attack
    GRAIL_TELEPORT(0x72),
    // 0x73 uhhh... not a full heal... happens after some boss?
    CHEST_UNLOCKED(0x74),
    CHEST_OPENED(0x75),
    //    SECRET_REVEALED(0x76), // only weapon blocks?
    // 0x77 uh... some enemy emerging from water? maybe?
    MENU_SELECT(0x78), // MENU_CONFIRM?
    MENU_CANCEL(0x79),
    // 0x80 a laser... maybe the actual palenque mini lasers laser
    // 0x81 some kinda water, post-bahamut kill?
    // 0x82 deep water splash? lemeza falling off boat after bahamut?
    TIAMAT_SCREAM(0x83),
    // 0x84 baphomet scream? some other miniboss death sound? maybe even certain normal enemies?
    MOTHER_WAIL(0x85),
    // 0x86 another guardian kill sound? sakit? wow maybe it's only if they die from a projectile/pistol?
    // 0x87 Kuusarikku (winged bull) death?
    // 0x88 uhh... some dimensional boss death? wolf?
    // 0x89 another scream like 0x84 but shorter
    SAVE_MENU_OPEN(0x90),
    SAVE_MENU_CLOSE(0x91),
    SAVE_LOADED(0x92),
    LAHAMU_COALESCE(0x93), // test (which is disperse?)
    // 0x94 flying extinction enemies dive attack?
    // 0x95 stronger fire projectile
    // 0x96 some kinda pot lid lol
    // 0x97 ellmac cart rattle?
    HP_HEAL(0x98),
    // 0x99 anubis death beam (not headless horse)
    // 0xa0 masked man jump?
    // 0xa1 enemy land?
    // 0xa2 another water sploosh
    LEMEZA_BURN(0xa3), // test
    LEMEZA_ZAP(0xa4), // test
    // 0xa5 projectile something
    // 0Xa6 illusion maiden appear/disappear? both?
    // TODO: time attack sounds?
    UNKNOWN(-1);

    companion object: ParamLookup<Sfx> {
        private val map = values().associateBy(Sfx::value)
        @JvmStatic override fun valueOf(value: Short) = map.getOrDefault(value, UNKNOWN)
    }
}