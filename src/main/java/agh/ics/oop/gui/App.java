package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {
    private AbstractWorldMap map;
    public void init() {
        try {
            MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
            this.map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            IEngine engine = new SimulationEngine(directions, this.map, positions);
            engine.run();
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
            Platform.exit();
        }
    }

    public void start(Stage primaryStage) {
        int width = 20;
        int height = 20;
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);

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
                    Label label = new Label(map.objectAt(new Vector2d(i, j)).toString());
                    grid.add(label, gridColumnIndex, gridRowIndex, 1, 1);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
            }
        }

        grid.getColumnConstraints().add(new ColumnConstraints(width));
        grid.getRowConstraints().add(new RowConstraints(height));

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}