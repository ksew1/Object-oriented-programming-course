package agh.ics.oop;

import java.util.concurrent.ThreadLocalRandom;

public class Animal extends AbstractWorldMapElement {
    private MapDirection direction;
    private IWorldMap map;

    public Animal(IWorldMap map) {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
        eatGrass(this.position);
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.direction = MapDirection.NORTH;
        if (map.canMoveTo(initialPosition)) {
            this.position = initialPosition;
            this.map = map;
        } else {
            this.position = new Vector2d(2, 2);
            map.place(this);
        }
    }


    public String toString() {
        return switch (this.direction) {
            case NORTH -> "N";
            case SOUTH -> "S";
            case EAST -> "E";
            case WEST -> "W";
        };
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case FORWARD -> {
                Vector2d newPosition = this.position.add(this.direction.toUnitVector());
                if (this.map.canMoveTo(newPosition)) {
                    eatGrass(newPosition);
                    this.position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.subtract(this.direction.toUnitVector());
                if (this.map.canMoveTo(newPosition)) {
                    eatGrass(newPosition);
                    this.position = newPosition;
                }
            }
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
        }
    }

    public MapDirection getDirection() {
        return this.direction;
    }

    private void eatGrass(Vector2d position) {
        AbstractWorldMapElement element = (AbstractWorldMapElement) this.map.objectAt(position);
        if (element instanceof Grass) {
            Grass grass = (Grass) element;
            GrassField grassField = (GrassField) this.map;

            int n = grassField.getN();

            int y = (int) Math.sqrt(n * 10);
            int x = (int) Math.sqrt(n * 10);
            Vector2d newPosition;
            do {
                newPosition = new Vector2d(ThreadLocalRandom.current().nextInt(0, x + 1),
                        ThreadLocalRandom.current().nextInt(0, y + 1));
            } while (this.map.isOccupied(newPosition));
            grass.setPosition(newPosition);
        }
    }
}
