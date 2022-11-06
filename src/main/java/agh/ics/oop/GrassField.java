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
            this.elements.add(new Grass(newPosition));
        }
    }

    @Override
    protected Vector2d getLowerLeft() {
        int x = elements.get(0).getPosition().x;
        int y = elements.get(0).getPosition().y;
        for (AbstractWorldMapElement element: this.elements) {
            x = Math.min(x, element.getPosition().x);
            y = Math.min(y, element.getPosition().y);
        }
        return new Vector2d(x, y);
    }

    @Override
    protected Vector2d getUpperRight() {
        int x = elements.get(0).getPosition().x;
        int y = elements.get(0).getPosition().y;
        for (AbstractWorldMapElement element: this.elements) {
            x = Math.max(x, element.getPosition().x);
            y = Math.max(y, element.getPosition().y);
        }
        return new Vector2d(x, y);
    }

    @Override
    protected boolean isInBorders(Vector2d position) {
        return position.follows(new Vector2d(0, 0));
    }

    public int getN() {
        return this.n;
    }
}
