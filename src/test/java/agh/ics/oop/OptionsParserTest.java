package agh.ics.oop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/* Odpowiedz na pytanie: jak zaimplementować mechanizm, który wyklucza pojawienie się
dwóch zwierząt w tym samym miejscu.

Odpowiedź:
Utworzyłbym, klasę Map. Posiadała by ona boolowksą tablicę 4 na 4.
Jeśli zwierzak już jest na damym polu było by one wypełnione true. W przeciwnym przypadku false.
Zaimplementował bym 3 metody. Przyjmowały by one współrzędne danego pola. Pierwsza metoda by na
podstawie współrzędnych zmieniała wartość na false, druga na true, a ostania metoda zwracała wartość
danego pola. Jeśli zwierzak by się pojawił na damym polu to z mieniamy wartość na true. Jeśli
Schodzi z niego to false. Przed wejściem zwierzaka sprawdzalibyśmy czy pole ma wartość false. Jeśli
tak to wchodzimy jeśli nie to nie. Złożoność O(1) */
public class OptionsParserTest {
    @Test
    public void parseTest() {
        OptionsParser parser = new OptionsParser();
        MoveDirection[] testArray = new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD,
                MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD};
        MoveDirection[] testArrayAnswer = parser.parse(new String[]{"f", "b", "left", "right", "l", "f", "r", "r",
                "forward",
                "backward"});
        for (int i = 0; i < testArray.length; i++) {
            Assertions.assertEquals(testArrayAnswer[i], testArray[i]);
        }

        String[] testException = new String[]{"burger", "f", "b", "e", "left", "right", "z", "y", "F", "word",
                "pizza", "pizza", "l", "f", "r", "r", "forward", "backward"};
        Assertions.assertThrows(IllegalArgumentException.class, () -> parser.parse(testException));
    }
}
