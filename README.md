# lmr-rcd
Kotlin package for editing La-Mulana Remake .rcd files. Compatible with Java and anything else that runs on the JVM.

Latest version: still WIP

### Getting started
1. Call `RcdParser.parse(Path rcdScriptPath)` to get a `World` tree of your .rcd file's data.
    * This method assumes that your .rcd file has an identical Zone-Scene-Screen hierarchy to the vanilla game.
        If it doesn't, you must pass a second argument `List<List<Short>> worldScreenCounts` to indicate the number of screens within each scene within each zone.
        The [lmr-msd](https://github.com/halgorithm/lmr-msd) library's `Stage.generateScreetCounts()` method can generate this data for you.

2. Edit your world tree's actors and effects.
    * `Actor` refers to a positioned screen object and `Effect` refers to a positionless behavioral object. `Entity` is the generic term for either.
    * Any entity can be wrapped in an `EntityDecorator` subclass that matches its type id, e.g. `Pot.wrap(my0x00Actor)`.
        These decorators allow you to edit objects in a strongly-typed and highly-readable way, e.g. `pot.setHitSound(Sfx.SNAKE_HISS)`. 
    * `Zone`, `Scene` and `Screen` instances all have query-building methods (TODO: document once implemented) for efficiently fetching and filtering objects from the world.
        Queries can return decorated with `.ofType(decoratorClass: Class<Decorator>)`
    * You can attach/detch objects at each level of the world with `addEffect(myEffect)` and `removeEffect(myEffect)`, and for screens also `addActor(myActor)` and `removeActor(myActor)`
    
3. Write your `World` back to a file using `RcdSerializer.serialize(World world, Path outputPath)`

### Documentation
More coming soon...