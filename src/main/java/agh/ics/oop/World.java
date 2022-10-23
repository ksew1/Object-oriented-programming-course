package agh.ics.oop;

public class World {

    public static void run(MoveDirection[] directions) {
        for (MoveDirection direction : directions) {
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

        Animal firstAnimal = new Animal();
        System.out.println(firstAnimal);
        MoveDirection[] directions = OptionsParser.parse(args);
        for (MoveDirection direction : directions) {
            firstAnimal.move(direction);

        }
        System.out.println(firstAnimal);
        System.out.println("Stop");

    }
}
