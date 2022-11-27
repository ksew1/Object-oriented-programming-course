package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable {
    private MoveDirection[] moves;
    private final IWorldMap map;
    private final List<Animal> animals = new ArrayList<>();
    private App app = null;
    private int delay = 500;


    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] startPositions) {
        this.moves = moves;
        this.map = map;
        for (Vector2d startPosition : startPositions) {
            Animal newAnimal = new Animal(this.map, startPosition);
            if (this.map.place(newAnimal)) {
                this.animals.add(newAnimal);
            }
        }
    }

    public SimulationEngine(IWorldMap map, Vector2d[] startPositions, App app, int delay) {
        this.map = map;
        this.delay = delay;
        this.app = app;

        for (Vector2d startPosition : startPositions) {
            Animal newAnimal = new Animal(this.map, startPosition);
            if (this.map.place(newAnimal)) {
                this.animals.add(newAnimal);
            }
        }
    }

    public void setMoves(MoveDirection[] moves) {
        this.moves = moves;
    }

    @Override
    public void run() {
        if (this.app == null) {
            if (animals.size() != 0) {
                for (int i = 0; i < this.moves.length; i++) {
                    this.animals.get(i % this.animals.size()).move(this.moves[i]);
                    System.out.println(this.map);
                }
            }
            return;
        }
        Platform.runLater(app::renderGrid);
        for (int i = 0; i < this.moves.length; i++) {
            animals.get(i % this.animals.size()).move(this.moves[i]);
            Platform.runLater(app::renderGrid);
            try {
                Thread.sleep(this.delay);
            } catch (InterruptedException e) {
                System.out.println("Error");
                throw new RuntimeException(e);
            }
        }
    }


}
