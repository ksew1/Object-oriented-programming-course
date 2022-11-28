package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GrassFieldTest {
    @Test
    public void canMoveToTest() {
        IWorldMap map = new GrassField(1);

        map.place(new Animal(map, new Vector2d(7, 4)));
        map.place(new Animal(map, new Vector2d(32, 2)));
        map.place(new Animal(map, new Vector2d(0, 1)));

        Assertions.assertTrue(map.canMoveTo(new Vector2d(100, 8)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(6, 6)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(7, 4)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(-1, 0)));
    }

    @Test
    public void placeTest() {
        IWorldMap map = new GrassField(1);
        Assertions.assertTrue(map.place(new Animal(map, new Vector2d(435, 845350))));
        Assertions.assertTrue(map.place(new Animal(map, new Vector2d(1, 1))));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> map.place(new Animal(map, new Vector2d(1, 1))));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> map.place(new Animal(map, new Vector2d(435, 845350))));
    }

    @Test
    public void isOccupiedTest() {
        int n = 1;
        IWorldMap map = new GrassField(n);

        map.place(new Animal(map, new Vector2d(0, 0)));
        map.place(new Animal(map, new Vector2d(1, 1)));
        map.place(new Animal(map, new Vector2d(2, 2)));
        for (int i = 0; i < (int) Math.sqrt(n * 10); i++) {
            for (int j = 0; j < (int) Math.sqrt(n * 10); j++) {
                if (map.objectAt(new Vector2d(i, j)) instanceof Grass) {
                    Assertions.assertTrue(map.isOccupied(new Vector2d(i, j)));
                }
            }
        }

        Assertions.assertTrue(map.isOccupied(new Vector2d(0, 0)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(1, 1)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(232, 3)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(5111, 532)));
    }


    @Test
    public void objectAtTest() {
        IWorldMap map = new GrassField(2);

        Animal testAnimal1 = new Animal(map, new Vector2d(2, 4));
        map.place(testAnimal1);
        Animal testAnimal2 = new Animal(map, new Vector2d(4, 3));
        map.place(testAnimal2);

        Assertions.assertEquals(testAnimal1, map.objectAt(new Vector2d(2, 4)));
        Assertions.assertNull(map.objectAt(new Vector2d(-1, 5)));
        Assertions.assertEquals(testAnimal2, map.objectAt(new Vector2d(4, 3)));
        Assertions.assertNull(map.objectAt(new Vector2d(323, 3)));
    }

}
