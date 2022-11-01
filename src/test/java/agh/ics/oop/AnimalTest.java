package agh.ics.oop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class AnimalTest {

    @Test
    public void orientationTest() {
        MapDirection[] testArrayAnswer = new MapDirection[]{MapDirection.WEST, MapDirection.NORTH, MapDirection.EAST, MapDirection.SOUTH, MapDirection.SOUTH, MapDirection.SOUTH, MapDirection.EAST, MapDirection.SOUTH};
        MoveDirection[] testArray = new MoveDirection[]{MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.RIGHT};

        Animal testAnimal = new Animal(new RectangularMap(10, 10));

        for (int i = 0; i < testArrayAnswer.length; i++) {
            testAnimal.move(testArray[i]);
            Assertions.assertEquals(testArrayAnswer[i], testAnimal.getDirection());
        }
    }

    @Test
    public void moveTest() {
        Vector2d[] testArrayAnswer = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 2), new Vector2d(4, 2), new Vector2d(4, 2), new Vector2d(4, 1), new Vector2d(4, 2), new Vector2d(4, 3), new Vector2d(4, 3)};

        MoveDirection[] testArray = new MoveDirection[]{MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT};

        Animal testAnimal = new Animal(new RectangularMap(10, 10));

        for (int i = 0; i < testArrayAnswer.length; i++) {
            testAnimal.move(testArray[i]);
            Assertions.assertTrue(testAnimal.isAt(testArrayAnswer[i]));
        }
    }

    @Test
    public void mapBorderTest() {
        Vector2d[] testArrayAnswer = new Vector2d[]{new Vector2d(2, 2), new Vector2d(2, 3), new Vector2d(2, 4), new Vector2d(2, 4), new Vector2d(2, 4), new Vector2d(1, 4), new Vector2d(0, 4), new Vector2d(0, 4), new Vector2d(0, 4), new Vector2d(0, 3), new Vector2d(0, 2), new Vector2d(0, 1), new Vector2d(0, 0), new Vector2d(0, 0), new Vector2d(1, 0), new Vector2d(2, 0), new Vector2d(3, 0), new Vector2d(4, 0), new Vector2d(4, 0), new Vector2d(4, 0)};

        MoveDirection[] testArray = new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT};

        Animal testAnimal = new Animal(new RectangularMap(5, 5));

        for (int i = 0; i < testArrayAnswer.length; i++) {
            Assertions.assertTrue(testAnimal.isAt(testArrayAnswer[i]));
            testAnimal.move(testArray[i]);
        }
    }
}
