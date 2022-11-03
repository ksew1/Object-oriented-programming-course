package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {
    private final MoveDirection[] moves;
    private final IWorldMap map;
    private final List<Animal> animals;


    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] startPositions) {
        this.moves = moves;
        this.map = map;
        this.animals = new ArrayList<>();
        for (Vector2d startPosition : startPositions) {
            Animal newAnimal = new Animal(this.map, startPosition);
            if (this.map.place(newAnimal)) {
                this.animals.add(newAnimal);
            }
        }
    }

    @Override
    public void run() {
        if (animals.size() != 0) {
            for (int i = 0; i < this.moves.length; i++) {
                this.animals.get(i % this.animals.size()).move(this.moves[i]);
                System.out.println(this.map);
            }
        }
    }
}
