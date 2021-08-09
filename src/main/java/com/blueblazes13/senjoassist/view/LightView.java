/*
 * This file has been made by Joey Koster.
 * Code may be used in any form, but I'm kindly
 * asking to note my name.
 */
package com.blueblazes13.senjoassist.view;

import com.blueblazes13.senjoassist.controls.SwitchButton;
import com.blueblazes13.senjoassist.model.HueModel;
import io.github.zeroone3010.yahueapi.Light;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 *
 * @author joeyk
 */
public class LightView extends Region {
    
    private AnchorPane background;
    private HueModel hue;
    private boolean isEnabled;
    
    public LightView(HueModel hue) {
        this.hue = hue;
        this.isEnabled = false;
        init();
    }
    
    
    public void init() {
        createButton();
    }

    
    private void createButton() {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream("lightbulb.png");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        Image image = new Image(stream);
        ImageView menuButton = new ImageView(image);
        menuButton.setFitHeight(50);
        menuButton.setFitWidth(50);
        menuButton.setLayoutX(5);
        menuButton.setLayoutY(5);
        menuButton.setOnMouseClicked(this::onTouch);
        
        this.background = new AnchorPane();
        this.background.setPrefSize(60, 60);
        this.background.setStyle("-fx-border-width: 2; -fx-border-color: #c1c1c1; -fx-border-radius: 10;");
        
        
        this.getChildren().addAll(this.background, menuButton);
    }
    
    
    private void onTouch(MouseEvent me) {
        this.background.getChildren().clear();
        
        if (this.isEnabled) {
            TimerTask taskX = new TimerTask(){
            @Override
            public void run() {
                for (int x = 0; x <= 200; x++) {
                    int i = x;
                    Platform.runLater(()->{
                        background.setPrefWidth(260 - i);
                    });
                    
                    try {
                        Thread.sleep((long) ((2/3f)*3f));
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            };

            Timer timerX = new Timer(true);
            timerX.schedule(taskX, 0);

            TimerTask taskY = new TimerTask(){
                @Override
                public void run() {
                    for (int y = 0; y <= 300; y++) {
                        int i = y;
                        Platform.runLater(()->{
                            background.setPrefHeight(360 - i);
                            background.setLayoutY(-300+i);
                        });

                        try {
                            Thread.sleep((long) ((2/3f)*2f));
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }

            };

            Timer timerY = new Timer(true);
            timerY.schedule(taskY, 0);
            
            background.getChildren().clear(); 
        } else {
            TimerTask taskX = new TimerTask(){
            @Override
            public void run() {
                for (int x = 0; x <= 200; x++) {
                    int i = x;
                    Platform.runLater(()->{
                        background.setPrefWidth(60 + i);
                    });
                    
                    try {
                        Thread.sleep((long) ((2/3f)*3f));
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            };

            Timer timerX = new Timer(true);
            timerX.schedule(taskX, 0);
            
            TimerTask taskButtons = new TimerTask(){
                @Override
                public void run() {
                    int i = 0;
                    for (Light light: hue.getRoom().getLights()) {
                        int j = i;
                        Platform.runLater(()->{
                            SwitchButton button = new SwitchButton();
                            button.setLayoutX(20);
                            button.setLayoutY(20 + 20*j);

                            button.setOnMouseClick(() -> {
                                if (button.isEnabled()) {
                                    light.turnOn();
                                } else {
                                    light.turnOff();
                                }
                                return null;
                            });

                            Label title = new Label(light.getName());
                            title.setLayoutX(80);
                            title.setLayoutY(17 + 20*j);
                            title.setAlignment(Pos.CENTER_LEFT);
                            title.setFont(Font.font("Segoe UI", 16));
                            title.setTextFill(Color.web("#e4e4e4"));
                            
                            background.getChildren().addAll(button, title);
                            
                            fadeIn(button);
                            fadeIn(title);
                        });
                        i++;
                    }
                }
            };
            Timer timerButtons = new Timer(true);

            TimerTask taskY = new TimerTask(){
                @Override
                public void run() {
                    for (int y = 0; y <= 300; y++) {
                        int i = y;
                        Platform.runLater(()->{
                            background.setPrefHeight(60 + i);
                            background.setLayoutY(-i);
                        });

                        try {
                            Thread.sleep((long) ((2/3f)*2f));
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    
                    timerButtons.schedule(taskButtons, 0);
                }

            };

            Timer timerY = new Timer(true);
            timerY.schedule(taskY, 0);

              
        }
        this.isEnabled = !this.isEnabled;
        
        
        
        
        
    }
    
    
    private void fadeIn(Node node) {
        final double x = node.getLayoutX();
        final double y = node.getLayoutY();
        System.out.println(y);
        MoveTo move = new MoveTo(x-10, y);
        LineTo line = new LineTo(x+10, y);
        Path path = new Path();
        path.getElements().addAll(move, line);
        
        PathTransition pathTrans = new PathTransition();
        pathTrans.setDuration(Duration.millis(200));
        pathTrans.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTrans.setCycleCount(1);
        pathTrans.setAutoReverse(false);
        pathTrans.setPath(path);
        pathTrans.setNode(node);
        pathTrans.play();
        
        node.setOpacity(0f);
        FadeTransition fadeTrans = new FadeTransition();
        fadeTrans.setAutoReverse(false);
        fadeTrans.setCycleCount(1);
        fadeTrans.setFromValue(0f);
        fadeTrans.setToValue(1f);
        fadeTrans.setDuration(Duration.millis(200));
        fadeTrans.setNode(node);
        fadeTrans.play();
    }
}
