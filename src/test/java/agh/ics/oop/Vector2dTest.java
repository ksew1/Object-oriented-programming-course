package agh.ics.oop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class Vector2dTest {
    @Test
    public void equalsTest() {
        Assertions.assertEquals(new Vector2d(1, 2), new Vector2d(1, 2));
        Assertions.assertNotEquals(new Vector2d(2, 1), new Vector2d(1, 2));
        Assertions.assertNotEquals(new Vector2d(0, -1), new Vector2d(0, 1));
        Assertions.assertNotEquals(Direction.FORWARD, new Vector2d(1, 0));
    }

    @Test
    public void toStringTest() {
        Assertions.assertEquals("(1,2)", new Vector2d(1, 2).toString());
        Assertions.assertEquals("(0,0)", new Vector2d(0, 0).toString());
        Assertions.assertNotEquals("(1,2)", new Vector2d(2, 2).toString());
        Assertions.assertNotEquals("3,2", new Vector2d(3, 2).toString());
    }

    @Test
    public void precedesTest() {
        Assertions.assertTrue(new Vector2d(1, 2).precedes(new Vector2d(1, 3)));
        Assertions.assertFalse(new Vector2d(4, 5).precedes(new Vector2d(1, 2)));
        Assertions.assertTrue(new Vector2d(4, 5).precedes(new Vector2d(4, 5)));
        Assertions.assertTrue(new Vector2d(4, 5).precedes(new Vector2d(7, 6)));
    }

    @Test
    public void followsTest() {
        Assertions.assertTrue(new Vector2d(1, 2).follows(new Vector2d(1, 1)));
        Assertions.assertTrue(new Vector2d(4, 5).follows(new Vector2d(1, 2)));
        Assertions.assertTrue(new Vector2d(4, 5).follows(new Vector2d(4, 5)));
        Assertions.assertFalse(new Vector2d(4, 5).follows(new Vector2d(7, 6)));
    }

    @Test
    public void upperRightTest() {
        Assertions.assertEquals(new Vector2d(1, 2), new Vector2d(1, 1).upperRight(new Vector2d(0, 2)));
        Assertions.assertEquals(new Vector2d(3, 2), new Vector2d(1, 1).upperRight(new Vector2d(3, 2)));
        Assertions.assertNotEquals(new Vector2d(4, 5), new Vector2d(4, 0).upperRight(new Vector2d(100, 2)));
        Assertions.assertEquals(new Vector2d(4, 5), new Vector2d(4, 5).upperRight(new Vector2d(4, 5)));

    }

    @Test
    public void lowerLeftTest() {
        Assertions.assertEquals(new Vector2d(-1, -2), new Vector2d(-1, 2).lowerLeft(new Vector2d(1, -2)));
        Assertions.assertNotEquals(new Vector2d(-3, -2), new Vector2d(-3, 2).lowerLeft(new Vector2d(1, -4)));
        Assertions.assertNotEquals(new Vector2d(0, 0), new Vector2d(0, 0).lowerLeft(new Vector2d(100, -3)));
        Assertions.assertEquals(new Vector2d(1, 1), new Vector2d(1, 1).lowerLeft(new Vector2d(6435, 123)));
    }

    @Test
    public void addTest() {
        Assertions.assertEquals(new Vector2d(4, 5), new Vector2d(4, 5).add(new Vector2d(0, 0)));
        Assertions.assertEquals(new Vector2d(-20, 15), new Vector2d(3, 17).add(new Vector2d(-23, -2)));
        Assertions.assertNotEquals(new Vector2d(0, 15), new Vector2d(100, 18).add(new Vector2d(-100, 3)));
        Assertions.assertNotEquals(new Vector2d(0, 7), new Vector2d(0, 0).add(new Vector2d(-293, -2)));
    }

    @Test
    public void subtractTest() {
        Assertions.assertEquals(new Vector2d(50, 3), new Vector2d(50, 6).subtract(new Vector2d(0, 3)));
        Assertions.assertEquals(new Vector2d(4, 4), new Vector2d(2, 2).subtract(new Vector2d(-2, -2)));
        Assertions.assertNotEquals(new Vector2d(4, 4), new Vector2d(-2, -2).subtract(new Vector2d(2, 2)));
        Assertions.assertNotEquals(new Vector2d(1, 1), new Vector2d(6, 2).subtract(new Vector2d(10, 23)));
    }

    @Test
    public void oppositeTest() {
        Assertions.assertEquals(new Vector2d(2, 2), new Vector2d(-2, -2).opposite());
        Assertions.assertEquals(new Vector2d(0, 0), new Vector2d(0, 0).opposite());
        Assertions.assertNotEquals(new Vector2d(4, 4), new Vector2d(-3, -3).opposite());
        Assertions.assertNotEquals(new Vector2d(123, 123), new Vector2d(123, 123).opposite());
    }

}
