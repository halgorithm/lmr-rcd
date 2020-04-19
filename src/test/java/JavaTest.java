import lmr.rcd.catalog.actors.Fog;
import lmr.rcd.catalog.actors.Pot;
import lmr.rcd.io.RcdParser;
import lmr.rcd.models.entity.*;
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

        var obj = new RcdObjectData((short) 0x05);
        var effect = new Effect(obj);
        var fog = Fog.wrap(effect);
        var fog2 = new Fog();
        var pot = new Pot();
        var potTests = pot.getTests();
        potTests.add(new Test((short) 1234, (byte) 1, TestOperator.BIT_AND_EQ_0));
        potTests.add(new Test((short) 1, (byte) 1, TestOperator.EQ));
        potTests.add(new Test((short) 2, (byte) 1, TestOperator.LT));
        var potUpdates = pot.getUpdates();
        potUpdates.add(new Update((short) 2, (byte) 1, UpdateOperator.ADD));

        System.out.println(fog.toDebugString());
        System.out.println();
        System.out.println(fog2.toDebugString());
        System.out.println();
        System.out.println(pot.toDebugString());

//        World world = RcdParser.parse(inputScriptPath);
//
//        for (var zone : world.getZones()) {
//            for (var scene : zone.getScenes()) {
//                var screens = scene.getScreens();
//                for (int i = 0; i < screens.size() / 2; i++) {
//                    var first = screens.get(i);
//
//                }
//            }
//        }
//        TODO: edits

//        RcdSerializer.serialize(world, buildScriptPath(outputGameDir));
    }

    static Path buildScriptPath(Path gameDir) {
        return Paths.get(gameDir.toString(), "/data/mapdata/script.rcd");
    }
}
