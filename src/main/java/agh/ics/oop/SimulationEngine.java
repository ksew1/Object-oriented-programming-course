package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {
    private final MoveDirection[] moves;
    private final IWorldMap map;
    private final List<Animal> listOfAnimals;


    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] startPositions) {
        this.moves = moves;
        this.map = map;
        this.listOfAnimals = new ArrayList<>();
        for (Vector2d startPosition : startPositions) {
            this.addAnimal(startPosition);
        }
    }

    public void addAnimal(Vector2d position) {
        Animal newAnimal = new Animal(this.map, position);
        if (map.place(newAnimal)) {
            this.listOfAnimals.add(newAnimal);
        }
    }

    @Override
    public void run() {
        if (listOfAnimals.size() != 0) {
            for (int i = 0; i < this.moves.length; i++) {
                Animal newAnimal = this.listOfAnimals.get(i % this.listOfAnimals.size());
                newAnimal.move(this.moves[i]);
                IWorldMap z = map;
            }
        }
    }
}
