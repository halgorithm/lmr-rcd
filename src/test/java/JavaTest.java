import lmr.rcd.io.RcdParser;
import lmr.rcd.models.hierarchy.World;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaTest {
    public static Path inputGameDir = Path.of("E:\\Games\\La-Mulana 1.0\\");
    public static Path outputGameDir = Path.of("E:\\Games\\La-Mulana 1.0 RCDTEST\\");

    public static void main(String args[]) throws IOException {
        var inputScriptPath = buildScriptPath(inputGameDir);

        World world = null;
        world = RcdParser.parse(inputScriptPath);

        for (var zone : world.getZones()) {
            for (var scene : zone.getScenes()) {
                var screens = scene.getScreens();
                for (int i = 0; i < screens.size() / 2; i++) {
                    // we want to swap screens on opposite ends
                    // then, we want to adjust the positions of their actors
                    // e.g. if we have this screen layout:
                    //    | 0 | 1 |
                    //    | 2 | 3 |
                    //    | 4 | 5 |
                    // then we will swap 0 and 1, then move all actors on the left side of 0 to the right side of 0
                    var first = screens.get(i);

                }
            }
        }
        // TODO: edits

//        RcdSerializer.serialize(world, buildScriptPath(outputGameDir));

        var yay = 2;

//        var world = new World();
//        var guidance = world.getZones().get(0);
//        var obj = new RcdObjectData((short) 0x0000);
//        Actor actor = new ActorImpl(obj);
//        var pot1 = Pot.wrap(actor);
//        var pot2 = new Pot();
//        pot2.setKind(Pot.Kind.INVALID); // Pot.Kind could also just be PotKind imported from catalog.enums.*, but probably doesn't reduce import count anyway
//        pot2.setQuantity(200);
    }

    static Path buildScriptPath(Path gameDir) {
        return Paths.get(gameDir.toString(), "/data/mapdata/script.rcd");
    }
}
