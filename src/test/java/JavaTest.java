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
