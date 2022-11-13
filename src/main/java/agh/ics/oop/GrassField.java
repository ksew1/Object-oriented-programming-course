package agh.ics.oop;

import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap {
    private final int n;

    public GrassField(int n) {
        this.n = n;
        int y = (int) Math.sqrt(n * 10);
        int x = (int) Math.sqrt(n * 10);

        for (int i = 0; i < n; i++) {
            Vector2d newPosition;
            do {
                newPosition = new Vector2d(ThreadLocalRandom.current().nextInt(0, x + 1),
                        ThreadLocalRandom.current().nextInt(0, y + 1));
            } while (this.isOccupied(newPosition));
            this.elements.put(newPosition, new Grass(newPosition));
        }
    }

    @Override
    protected Vector2d getLowerLeft() {
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        for (Vector2d key : this.elements.keySet()) {
            x = Math.min(x, key.x);
            y = Math.min(y, key.y);
        }
        return new Vector2d(x, y);
    }

    @Override
    protected Vector2d getUpperRight() {
        int x = Integer.MIN_VALUE;
        int y = Integer.MIN_VALUE;
        for (Vector2d key : this.elements.keySet()) {
            x = Math.max(x, key.x);
            y = Math.max(y, key.y);
        }
        return new Vector2d(x, y);
    }

    @Override
    protected boolean isInBorders(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) &&
                position.precedes(new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    public int getN() {
        return this.n;
    }
}
