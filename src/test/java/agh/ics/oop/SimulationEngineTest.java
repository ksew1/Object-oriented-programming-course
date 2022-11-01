package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimulationEngineTest {

    @Test
    public void runTest() {
        String[] args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Assertions.assertNotNull(map.objectAt(new Vector2d(3, 4)));
        Assertions.assertNotNull(map.objectAt(new Vector2d(2, 0)));

        String[] args2 = new String[]{"r", "f", "r", "f", "f", "f", "f", "f", "f", "r", "f", "f", "r", "f", "f", "f"};
        MoveDirection[] directions2 = OptionsParser.parse(args2);
        IWorldMap map2 = new RectangularMap(5, 5);
        Vector2d[] positions2 = {new Vector2d(4, 4), new Vector2d(0, 0)};
        IEngine engine2 = new SimulationEngine(directions2, map2, positions2);
        engine2.run();

        Assertions.assertNotNull(map2.objectAt(new Vector2d(3, 0)));
        Assertions.assertNotNull(map2.objectAt(new Vector2d(3, 4)));
    }
}
