package lmr.rcd.query

import lmr.rcd.models.entity.EntityInterface

class ZoneQuery<T: EntityInterface> {
    // QUERY MODEL
    // each of these returns an EntityQuery<W> that has fun fetch(): List<W> (W can be Actor)
    // they are sugar for e.g. EntityQuery<Pot>(zoneIds: List<Short?>, sceneIds: List<Short?>, screenIds: List<Short?>, wrappers: List<W?>)
    //     WorldQuery: zones or NOT zones, wrappers or NOT wrappers
    //     ZoneQuery: scenes or NOT scenes, wrappers or NOT wrappers
    //     SceneQuery: screens or NOT screens, wrappers or NOT wrappers
    //     ScreenQuery: wrappers or NOT wrappers
    //     all have .ofNotType(W), .ofNotType(List<W>) with validations if you already specified positives
    //     each except ScreenXQuery have inChildTypes() and notInChildTypes(), where e.g. Zone's ChildTypes is Scenes
    //     all have .fetch() that returns List<T> where T is the type of the query that gets transformed by .ofType(single: W)

    // example:
    //     val guidanceActorsQuery: ZoneActorsQuery<Actor> = world.zones.get(0).actors()
    //     val stuffQuery: ZoneActorsQuery<Actor> = guidanceActors.inScenes(1..7).ofNotType(ObjectType.Enemies)
    //     val things = stuffQuery.fetch() // List<Actor>

    //     val sceneFxQuery: WorldEffectsQuery<Effect> = world.sceneEffects()
    //     val someSceneFxQuery: WorldEffectsQuery<Effect> = sceneFxQuery.inNotZones(listOf(0, 1, 2))
    //     val fogsQuery: WorldEffectsQuery<Fog> = someSceneFxQuery.ofType(Fog)
    //     val problematicSpotlightFogs = fogsQuery.fetch().filter { fog -> fog.isSpotlight() } // List<Fog>

    //     val scene: Scene = world.zones.get(0).scenes.get(0)
    //     val sceneFxQuery: SceneEffectsQuery<Timer> = scene.sceneEffects().ofType(Timer)
}