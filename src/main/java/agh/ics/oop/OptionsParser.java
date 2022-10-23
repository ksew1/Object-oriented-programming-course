package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        List<MoveDirection> list = new ArrayList<>();
        for (String arg: args) {
            switch (arg) {
                case "f", "forward" -> list.add(MoveDirection.FORWARD);
                case "b", "backward" -> list.add(MoveDirection.BACKWARD);
                case "r", "right" -> list.add(MoveDirection.RIGHT);
                case "l", "left" -> list.add(MoveDirection.LEFT);
            }
        }
        return list.toArray(new MoveDirection[0]);
    }
}
