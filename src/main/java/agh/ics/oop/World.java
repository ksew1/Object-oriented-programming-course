package agh.ics.oop;

public class World {
    public static Direction[] stringToEnum(String[] args) {
        Direction[] directions = new Direction[args.length];
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "f" -> directions[i] = Direction.FORWARD;
                case "b" -> directions[i] = Direction.BACKWARD;
                case "r" -> directions[i] = Direction.RIGHT;
                case "l" -> directions[i] = Direction.LEFT;
            }
        }

        return directions;
    }

    public static void run(Direction[] directions) {
        for (Direction direction : directions) {
            switch (direction) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak skręca w prawo");
                case LEFT -> System.out.println("Zwierzak skręca w lewo");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Start");
        World.run(stringToEnum(args));
        System.out.println("Stop");
    }
}
