package agh.ics.oop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class OptionsParserTest {

    @Test
    public void parseTest() {
        MoveDirection[] testArray = new MoveDirection[]{MoveDirection.FORWARD,
                MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.FORWARD,
                MoveDirection.RIGHT, MoveDirection.RIGHT};
        MoveDirection[] testArrayAnswer = OptionsParser.parse(new String[]{"burger", "f", "b", "e", "left",
                "right", "z", "y", "F", "word", "pizza", "pizza", "l", "f", "r", "r"});
        for (int i = 0; i < testArrayAnswer.length; i++) {
            Assertions.assertEquals(testArrayAnswer[i], testArray[i]);
        }
    }
}
