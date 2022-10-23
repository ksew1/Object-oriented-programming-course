package agh.ics.oop;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        int len = 0;
        for (String arg : args) {
            if (arg.equals("f") || arg.equals("b") || arg.equals("r") || arg.equals("l")) {
                len++;
            }
        }
        MoveDirection[] directions = new MoveDirection[len];
        int i = 0;
        for (String arg : args) {
            switch (arg) {
                case "f" -> directions[i] = MoveDirection.FORWARD;
                case "b" -> directions[i] = MoveDirection.BACKWARD;
                case "r" -> directions[i] = MoveDirection.RIGHT;
                case "l" -> directions[i] = MoveDirection.LEFT;
                default -> i--;
            }
            i++;
        }
        return directions;
    }
}
