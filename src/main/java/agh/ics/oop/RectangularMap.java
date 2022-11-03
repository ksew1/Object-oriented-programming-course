package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {
    public final ArrayList<Animal> animals = new ArrayList<>();
    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        this.height = height;
        this.width = width;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(this.width - 1, this.height - 1)) && !this.isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal: this.animals) {
            if (animal.isAt(position)) {
                return animal;
            }
        }
        return null;
    }

    public String toString() {
        return new MapVisualizer(this).draw(new Vector2d(0, 0), new Vector2d(this.width - 1, this.height - 1));
    }
}
