package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox extends Node {
    VBox vbox = new VBox(1);
    public GuiElementBox(IMapElement element) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(new File("").getAbsolutePath() + element.getImage()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        this.vbox.setAlignment(Pos.CENTER);
        this.vbox.getChildren().addAll(imageView, new Label(element.getLabel()));
    }
    @Override
    public Node getStyleableNode() {
        return this.vbox;
    }
}
