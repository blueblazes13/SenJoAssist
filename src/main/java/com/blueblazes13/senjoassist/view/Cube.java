/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blueblazes13.senjoassist.view;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import static javafx.scene.shape.Box.DEFAULT_SIZE;
import javafx.scene.shape.DrawMode;

/**
 *
 * @author joeyk
 */
public class Cube extends Box {
    
    Cube model;
    int x;
    int y;
    
    public Cube() {
        super();
        this.model = this;
        update();
    }
    
    
    public void update() {
        PhongMaterial mat = new PhongMaterial();
        mat.setDiffuseColor(Color.web("#f0b762"));
        
        super.setMaterial(mat);
        super.setDrawMode(DrawMode.FILL);
        this.setSize(100, 100, 100);
    }
    
    
    public void startMove() {
        super.setRotationAxis(new Point3D(10, 5, 3));
        TimerTask rotateTask = new TimerTask(){
            @Override
            public void run() {
                while (true) {
                    for (double x = 0; x <= 360; x += 1) {
                        double i = x;
                        Platform.runLater(()->{model.setRotate(i);});

                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        };
        Timer rotateTimer = new Timer(true);
        rotateTimer.schedule(rotateTask, 0);
        
        TimerTask moveTask = new TimerTask(){
            @Override
            public void run() {
                while (true) {
                    for (double y = 0; y <= 180; y += 0.02) {
                        double i = y;
                        Platform.runLater(()->{model.setLayoutY(model.y + 30*Math.sin(i));});

                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        };
        Timer moveTimer = new Timer(true);
        moveTimer.schedule(moveTask, 0);
    }
    
    
    public void setPos(int x, int y) {
        super.setLayoutX(x);
        super.setLayoutY(y);
        this.x = x;
        this.y = y;
    }
    
    
    public void setSize(int width, int height, int depth) {
        super.setWidth(width);
        super.setHeight(height);
        super.setDepth(depth);
    }
    
}
