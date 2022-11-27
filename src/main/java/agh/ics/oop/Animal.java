package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;


public class Animal extends AbstractWorldMapElement {
    private MapDirection direction;
    private final IWorldMap map;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map) {

        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
        this.map = map;
        addObserver((IPositionChangeObserver) this.map);

    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.direction = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
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
                    this.position = newPosition;
                    positionChanged(oldPosition);
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.subtract(this.direction.toUnitVector());
                Vector2d oldPosition = this.position;
                if (this.map.canMoveTo(newPosition)) {
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

    @Override
    public String getLabel() {
        return "Z " + this.getPosition();
    }
    @Override
    public String getImage() {
        return switch (this.direction) {
            case NORTH -> "/src/main/resources/up.png";
            case SOUTH -> "/src/main/resources/down.png";
            case EAST -> "/src/main/resources/right.png";
            case WEST -> "/src/main/resources/left.png";
        };
    }
}
