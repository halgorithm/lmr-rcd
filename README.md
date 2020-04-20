# lmr-rcd
Kotlin package for editing La-Mulana Remake .rcd files. Compatible with Java and anything else that runs on the JVM.

Latest version: still WIP

### Getting started
1. Call `RcdParser.parse(Path rcdScriptPath)` to get a `World` tree of your .rcd file's data.
    * This method assumes that your script.rcd has identical Zone-Scene-Screen hierarchy counts to the vanilla game.
        If it doesn't, you must pass a second argument `List<List<Byte>> worldScreenCounts` to indicate the number of screens within each scene within each zone.
        The [lmr-msd](https://github.com/halgorithm/lmr-msd) library's `Stage.generateSceneScreenCounts()` method can generate screen counts for each .msd.

2. Edit your world tree's actors and effects.
    * `Actor` refers to a positioned screen object and `Effect` refers to a positionless behavioral object.
    `Entity` is the generic term for either.
    * (Coming soon) Decorator classes are provided for every entity type the rcd format supports. You may wrap an existing
        actor via `pot = Pot.wrap(myActor)`, or use the constructor `new Pot()` to implicitly instantiate an actor with
        sensible default values for the params the type expects. Decorators have enum-typed accessors for any enum-style
        params the type accepts, e.g. `pot.setHitSound(Sfx.SNAKE_HISS)`. They also have simple numeric accessors for other
        params which validate that the provided value falls within the expected range for the param, e.g.
        `pot.setQuantity(-100)` will throw an `IllegalArgumentException`.        
            * If you find that the param validation logic for a decorator is incorrect somehow, you can bypass it by
            using `setParam()` like so: `myPot.setParam(Pot.Param.DROP_TYPE, -120, false)` (the last argument skips
            validations). You can also directly access the underlying `List<Short>` of params if you wish.
            * PRs to fix incorrect validation logic are welcome. The simplest way to do so would be to edit [this rcdtype]
            file (the format is described [here]) and then run [this task] to regenerate the decorator class files.
    * (Coming soon) `Zone`, `Scene` and `Screen` instances all have query-building methods for efficiently fetching and
        filtering entities. Queries for specific entity types using `.ofType(decoratorClass: Class<Decorator>)` will return
        a list of decorators instead of basic actors.
    * You can attach/detach effects to any hiearchy level with `addEffect(myEffect)` and `removeEffect(myEffect)`, and
    screens also can `addActor(myActor)` and `removeActor(myActor)` (you can pass decorators too).
    
3. Write your `World` back to a file using `RcdSerializer.serialize(World world, Path outputPath)`

### Documentation
More coming soon...