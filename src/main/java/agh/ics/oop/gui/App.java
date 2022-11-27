package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class App extends Application {
    private AbstractWorldMap map;
    private final GridPane grid = new GridPane();
    private SimulationEngine engine;

    public void init() {
        try {
            this.map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            this.engine = new SimulationEngine(this.map, positions, this, 300);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    public void renderGrid() {
        int width = 50;
        int height = 50;

        this.grid.setGridLinesVisible(false);
        this.grid.getChildren().clear();
        this.grid.getColumnConstraints().clear();
        this.grid.getRowConstraints().clear();
        this.grid.setGridLinesVisible(true);

        Label mainLabel = new Label("y/x");
        GridPane.setHalignment(mainLabel, HPos.CENTER);

        System.out.println(map);
        grid.add(mainLabel, 0, 0, 1, 1);

        for (int i = map.getLowerLeft().x, gridColumnIndex = 1; i < map.getUpperRight().x + 1; i++, gridColumnIndex++) {
            Label label = new Label(String.valueOf(i));
            grid.add(label, gridColumnIndex, 0, 1, 1);
            grid.getColumnConstraints().add(new ColumnConstraints(width));
            GridPane.setHalignment(label, HPos.CENTER);
        }


        for (int i = map.getUpperRight().y, gridRowIndex = 1; i > map.getLowerLeft().y - 1; i--, gridRowIndex++) {
            Label label = new Label(String.valueOf(i));
            grid.add(label, 0, gridRowIndex, 1, 1);
            grid.getRowConstraints().add(new RowConstraints(height));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = map.getLowerLeft().x, gridColumnIndex = 1; i < map.getUpperRight().x + 1; i++, gridColumnIndex++) {
            for (int j = map.getUpperRight().y, gridRowIndex = 1; j > map.getLowerLeft().y - 1; j--, gridRowIndex++) {
                if (map.objectAt(new Vector2d(i, j)) != null) {
                    GuiElementBox element;
                    try {
                        element = new GuiElementBox((IMapElement) map.objectAt(new Vector2d(i, j)));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    grid.add(element.getStyleableNode(), gridColumnIndex, gridRowIndex, 1, 1);
                }
            }
        }
        grid.getColumnConstraints().add(new ColumnConstraints(width));
        grid.getRowConstraints().add(new RowConstraints(height));
    }

    public void start(Stage primaryStage) {
        renderGrid();
        OptionsParser parser = new OptionsParser();

        Button start = new Button("start");
        TextField moveDirectionsInput = new TextField();
        VBox gui = new VBox(new HBox(moveDirectionsInput, start), this.grid);
        gui.setSpacing(20);

        start.setOnAction((click) -> {
            MoveDirection[] moveDirections = parser.parse(moveDirectionsInput.getText().split(" "));
            this.engine.setMoves(moveDirections);
            Thread newEngineThread = new Thread(this.engine);
            newEngineThread.start();
            moveDirectionsInput.clear();
        });
        Scene scene = new Scene(gui, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}