package com.blueblazes13.senjoassist.view;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class SquareView extends Region {
    
    public static int HEIGHT = 180;
    public static int WIDTH = 180;

    public SquareView() {
        update();
    }


    /**
     * Updates the complete view and loads the specified content
     * 
     * @param content The content to load on the view
     */
    public void update(Node content) {
        AnchorPane apBack = new AnchorPane();
        apBack.setPrefSize(WIDTH, HEIGHT);
        apBack.setMaxSize(WIDTH, HEIGHT);
        apBack.setMinSize(WIDTH, HEIGHT);
        apBack.setStyle("-fx-background-color:transparent;-fx-border-width:5;-fx-border-radius:30;-fx-border-color:white;");

        AnchorPane apFront = new AnchorPane();
        apFront.setPrefSize(170, 170);
        apFront.setMaxSize(170, 170);
        apFront.setMinSize(170, 170);
        apFront.setLayoutX(5);
        apFront.setLayoutY(5);
        apFront.setStyle("-fx-background-color:rgba(255, 255, 255, 0.3);-fx-background-radius:30;");

        this.getChildren().addAll(apBack, apFront);
        if (content != null) this.getChildren().add(content);
    }

    
    /**
     * Updates the complete view
     */
    public void update() {
        this.update(null);
    }

}
