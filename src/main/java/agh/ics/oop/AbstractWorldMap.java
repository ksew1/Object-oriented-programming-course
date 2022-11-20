package agh.ics.oop;


import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected final Map<Vector2d, AbstractWorldMapElement> elements = new HashMap<>();
    protected MapBoundary mapBoundary = new MapBoundary(this);

    protected abstract boolean isInBorders(Vector2d position);

    public abstract Vector2d getLowerLeft();

    public abstract Vector2d getUpperRight();


    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.elements.put(animal.getPosition(), animal);
            this.mapBoundary.addPosition(animal.getPosition());
            return true;
        }
        throw new IllegalArgumentException("Position " + animal.getPosition() + "is not available");
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
        this.mapBoundary.positionChanged(oldPosition, newPosition);
    }

    public String toString() {
        return new MapVisualizer(this).draw(this.getLowerLeft(), this.getUpperRight());
    }
}
