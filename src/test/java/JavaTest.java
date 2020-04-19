import lmr.rcd.catalog.actors.Fog;
import lmr.rcd.io.RcdParser;
import lmr.rcd.models.entity.Actor;
import lmr.rcd.models.entity.Effect;
import lmr.rcd.models.entity.RcdObjectData;
import lmr.rcd.models.hierarchy.World;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JavaTest {
    public static Path inputGameDir = Path.of("E:\\Games\\La-Mulana 1.0\\");
    public static Path outputGameDir = Path.of("E:\\Games\\La-Mulana 1.0 RCDTEST\\");

    public static void main(String args[]) throws IOException {
        var inputScriptPath = buildScriptPath(inputGameDir);

        var obj = new RcdObjectData((short) 0x92);
        var actor = new Effect(obj);
        var fog = Fog.wrap(actor);

        World world = null;
        world = RcdParser.parse(inputScriptPath);

        for (var zone : world.getZones()) {
            for (var scene : zone.getScenes()) {
                var screens = scene.getScreens();
                for (int i = 0; i < screens.size() / 2; i++) {
                    var first = screens.get(i);

                }
            }
        }
//        TODO: edits

//        RcdSerializer.serialize(world, buildScriptPath(outputGameDir));
    }

    static Path buildScriptPath(Path gameDir) {
        return Paths.get(gameDir.toString(), "/data/mapdata/script.rcd");
    }
}
