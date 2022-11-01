package agh.ics.oop;

public class RectangularMap implements IWorldMap {
    private final Animal[][] map;
    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        this.map = new Animal[height][width];
        this.height = height;
        this.width = width;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(this.width - 1, this.height - 1)) && !this.isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())) {
            this.map[animal.getPosition().y][animal.getPosition().x] = animal;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(this.width - 1, this.height - 1))) {
            return this.objectAt(position) != null;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(this.width - 1, this.height - 1))) {
            return this.map[position.y][position.x];
        }
        return null;
    }

    public String toString() {
        MapVisualizer newVisualizer = new MapVisualizer(this);
        return newVisualizer.draw(new Vector2d(0, 0), new Vector2d(this.width - 1, this.height - 1));
    }

    // Można zmodyfikować metody place, żeby wstawiała nulle, ale wydaje mi się, że odanie tej metody,
    // zwiększa czytelność kodu
    @Override
    public void setNull(Vector2d position) {
        if (position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(this.width - 1, this.height - 1))) {
            this.map[position.y][position.x] = null;
        }

    }
}
