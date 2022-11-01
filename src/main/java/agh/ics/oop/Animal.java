package agh.ics.oop;

public class Animal {
    private MapDirection direction;
    private Vector2d position;
    private IWorldMap map;

    public Animal(IWorldMap map) {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
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
            case NORTH -> this.position + ",N";
            case SOUTH -> this.position + ",S";
            case EAST -> this.position + ",E";
            case WEST -> this.position + ",W";
        };
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case FORWARD -> {
                Vector2d newPosition = this.position.add(this.direction.toUnitVector());
                if (this.map.canMoveTo(newPosition)) {
                    this.map.setNull(this.position);
                    this.position = newPosition;
                    this.map.place(this);
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.subtract(this.direction.toUnitVector());
                if (this.map.canMoveTo(newPosition)) {
                    this.map.setNull(this.position);
                    this.position = newPosition;
                    this.map.place(this);
                }
            }
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
        }
    }

    public MapDirection getDirection() {
        return this.direction;
    }

    public Vector2d getPosition() {
        return this.position;
    }
}
