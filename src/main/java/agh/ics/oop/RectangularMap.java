package agh.ics.oop;


public class RectangularMap extends AbstractWorldMap {
    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        this.height = height;
        this.width = width;
    }

    @Override
    protected Vector2d getLowerLeft() {
        return new Vector2d(0, 0);
    }

    @Override
    protected Vector2d getUpperRight() {
        return new Vector2d(this.width-1, this.height-1);
    }

    @Override
    protected boolean isInBorders(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) &&
                position.precedes(new Vector2d(this.width - 1, this.height - 1));
    }
}
