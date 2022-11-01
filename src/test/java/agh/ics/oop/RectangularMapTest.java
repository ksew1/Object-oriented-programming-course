package agh.ics.oop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class RectangularMapTest {

    @Test
    public void canMoveToTest() {
        IWorldMap map = new RectangularMap(5, 5);

        map.place(new Animal(map, new Vector2d(2, 3)));
        map.place(new Animal(map, new Vector2d(4, 4)));
        map.place(new Animal(map, new Vector2d(0, 1)));

        Assertions.assertTrue(map.canMoveTo(new Vector2d(2, 4)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(4, 3)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(2, 3)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(5, 5)));
    }

    @Test
    public void placeTest() {
        IWorldMap map = new RectangularMap(5, 5);
        Assertions.assertTrue(map.place(new Animal(map, new Vector2d(2, 3))));
        Assertions.assertTrue(map.place(new Animal(map, new Vector2d(3, 2))));
        Assertions.assertFalse(map.place(new Animal(map, new Vector2d(2, 3))));
        Assertions.assertFalse(map.place(new Animal(map, new Vector2d(5, 6))));
    }

    @Test
    public void isOccupiedTest() {
        IWorldMap map = new RectangularMap(5, 5);

        map.place(new Animal(map, new Vector2d(2, 4)));
        map.place(new Animal(map, new Vector2d(4, 3)));
        map.place(new Animal(map, new Vector2d(0, 1)));

        Assertions.assertTrue(map.isOccupied(new Vector2d(2, 4)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(4, 3)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(2, 3)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(5, 5)));
    }

    @Test
    public void objectAtTest() {
        IWorldMap map = new RectangularMap(5, 5);

        Animal testAnimal1 = new Animal(map, new Vector2d(2, 4));
        map.place(testAnimal1);
        Animal testAnimal2 = new Animal(map, new Vector2d(4, 3));
        map.place(testAnimal2);
        Animal testAnimal3 = new Animal(map, new Vector2d(0, 1));
        map.place(testAnimal3);

        Assertions.assertEquals(testAnimal1, map.objectAt(new Vector2d(2, 4)));
        Assertions.assertNull(map.objectAt(new Vector2d(5, 5)));
        Assertions.assertEquals(testAnimal2, map.objectAt(new Vector2d(4, 3)));
        Assertions.assertNull(map.objectAt(new Vector2d(3, 3)));
    }

}
