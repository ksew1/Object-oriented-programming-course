package agh.ics.oop;

import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap {
    protected ArrayList<AbstractWorldMapElement> elements = new ArrayList<>();

    protected abstract boolean isInBorders(Vector2d position);

    protected abstract Vector2d getLowerLeft();

    protected abstract Vector2d getUpperRight();

    @Override
    public boolean canMoveTo(Vector2d position) {
        return isInBorders(position) && !(objectAt(position) instanceof Animal);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.elements.add(animal);
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
        for (AbstractWorldMapElement element : this.elements) {
            if (element.isAt(position) && element instanceof Animal) {
                return element;
            }
        }
        for (AbstractWorldMapElement element : this.elements) {
            if (element.isAt(position)) {
                return element;
            }
        }
        return null;
    }

    public String toString() {
        return new MapVisualizer(this).draw(this.getLowerLeft(), this.getUpperRight());
    }
}
