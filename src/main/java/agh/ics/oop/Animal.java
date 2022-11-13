package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Animal extends AbstractWorldMapElement {
    private MapDirection direction;
    private IWorldMap map;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map) {

        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
        this.map = map;
        this.eatGrass(this.position);
        addObserver((IPositionChangeObserver) this.map);

    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.direction = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
        this.eatGrass(this.position);
        addObserver((IPositionChangeObserver) this.map);
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
                Vector2d oldPosition = this.position;
                if (this.map.canMoveTo(newPosition)) {
                    eatGrass(newPosition);
                    this.position = newPosition;
                    positionChanged(oldPosition);
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.subtract(this.direction.toUnitVector());
                Vector2d oldPosition = this.position;
                if (this.map.canMoveTo(newPosition)) {
                    eatGrass(newPosition);
                    this.position = newPosition;
                    positionChanged(oldPosition);
                }
            }
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
        }
    }

    public MapDirection getDirection() {
        return this.direction;
    }

    void addObserver(IPositionChangeObserver observer) {
        this.observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer) {
        this.observers.remove(observer);
    }

    void positionChanged(Vector2d position) {
        for (IPositionChangeObserver observer : this.observers) {
            observer.positionChanged(position, this.position);
        }
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
