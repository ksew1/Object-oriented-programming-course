package agh.ics.oop;

public class Grass extends AbstractWorldMapElement {
    public Grass(Vector2d position) {
        this.position = position;
    }
    public String toString() {
        return "*";
    }
    public void setPosition(Vector2d position) {
        this.position = position;
    }
}
