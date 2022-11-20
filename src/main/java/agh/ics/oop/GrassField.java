package agh.ics.oop;

import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap {
    private final int n;

    public GrassField(int n) {
        this.n = n;

        for (int i = 0; i < n; i++) {
            createGrass();
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (objectAt(position) instanceof Grass) {
            this.elements.remove(position);
            this.mapBoundary.removePosition(position);
            createGrass();
        }
        return isInBorders(position) && !(objectAt(position) instanceof Animal);
    }

    @Override
    public Vector2d getLowerLeft() {
        return mapBoundary.getLowerLeft();
    }

    @Override
    public Vector2d getUpperRight() {
        return mapBoundary.getUpperRight();
    }

    @Override
    protected boolean isInBorders(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) &&
                position.precedes(new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    public void createGrass() {
        int y = (int) Math.sqrt(this.n * 10);
        int x = (int) Math.sqrt(this.n * 10);
        Vector2d newPosition;
        do {
            newPosition = new Vector2d(ThreadLocalRandom.current().nextInt(0, x + 1),
                    ThreadLocalRandom.current().nextInt(0, y + 1));
        } while (this.isOccupied(newPosition));
        this.elements.put(newPosition, new Grass(newPosition));
        mapBoundary.addPosition(newPosition);
    }
}
