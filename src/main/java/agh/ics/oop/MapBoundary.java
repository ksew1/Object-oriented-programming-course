package agh.ics.oop;


import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    private final AbstractWorldMap map;
    private final SortedSet<Vector2d> xCoordinatesOrder;

    private final SortedSet<Vector2d> yCoordinatesOrder;

    public MapBoundary(AbstractWorldMap map) {
        this.map = map;
        this.xCoordinatesOrder = new TreeSet<>((o1, o2) -> {
            if (o1.x != o2.x) {
                return o1.x - o2.x;
            }

            if (o1.y != o2.y) {
                return o1.y - o2.y;
            }

            if (this.map.objectAt(o1) instanceof Animal) {
                return 1;
            }

            return 0;
        });
        this.yCoordinatesOrder = new TreeSet<>((o1, o2) -> {
            if (o1.y != o2.y) {
                return o1.y - o2.y;
            }

            if (o1.x != o2.x) {
                return o1.x - o2.x;
            }

            if (map.objectAt(o1) instanceof Animal) {
                return 1;
            }
            return 0;
        });
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        removePosition(oldPosition);
        addPosition(newPosition);
    }

    public void addPosition(Vector2d position) {
        this.xCoordinatesOrder.add(position);
        this.yCoordinatesOrder.add(position);
    }

    public void removePosition(Vector2d position) {
        this.xCoordinatesOrder.remove(position);
        this.yCoordinatesOrder.remove(position);
    }

    public Vector2d getUpperRight() {
        return this.xCoordinatesOrder.last().upperRight(this.yCoordinatesOrder.last());
    }

    public Vector2d getLowerLeft() {
        return this.xCoordinatesOrder.first().lowerLeft(this.yCoordinatesOrder.first());
    }
}
