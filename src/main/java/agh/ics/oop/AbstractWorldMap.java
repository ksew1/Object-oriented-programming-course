package agh.ics.oop;


import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected final Map<Vector2d, AbstractWorldMapElement> elements = new HashMap<>();

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
            this.elements.put(animal.getPosition(), animal);
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
        return this.elements.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement animal = this.elements.get(oldPosition);
        this.elements.remove(oldPosition);
        this.elements.put(newPosition, animal);
    }

    public String toString() {
        return new MapVisualizer(this).draw(this.getLowerLeft(), this.getUpperRight());
    }
}
