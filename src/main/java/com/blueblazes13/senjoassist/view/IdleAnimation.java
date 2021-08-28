/*
 * This file has been made by Joey Koster.
 * Code may be used in any form, but I'm kindly
 * asking to note my name.
 */
package com.blueblazes13.senjoassist.view;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author joeyk
 */
public class IdleAnimation extends Region {
    
    private ArrayList<Rectangle> rects;
    private final static int WIDTH = 25;
    
    public IdleAnimation() {
        this.rects = new ArrayList<>();
        Init();
    }
    
    
    private void Init() {
        this.getChildren().clear();
        
        for (int i = 0; i < IdleAnimation.WIDTH; i++) {
            Rectangle rect = new Rectangle(6, 50, Color.web("#f0b762"));
            rect.setLayoutX(i*9);
            rect.setArcHeight(6);
            rect.setArcWidth(6);
            this.rects.add(rect);
        }
        
        this.getChildren().addAll(rects);
    }
    
    
    public void playSlideAnimation() {
        for (int i = 12; i>= 0; i--) {
            int j = i;
            TimerTask animationTask = new TimerTask(){
            
            @Override
            public void run() {
                System.out.println(j);
                    try {
                        Thread.sleep(200*j);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(IdleAnimation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    ScaleTransition scaleTrans = new ScaleTransition();
                    scaleTrans.setToY(2);
                    scaleTrans.setFromY(1);
                    scaleTrans.setDuration(Duration.millis(1000));
                    scaleTrans.setNode(rects.get(j));
                    scaleTrans.play();
            }
        };
        
        Timer timer = new Timer(true);
        timer.schedule(animationTask, 0);
        }
        
        for (int i = 0; i<= 24; i++) {
            int j = i;
            TimerTask animationTask = new TimerTask(){
            
            @Override
            public void run() {
                    try {
                        Thread.sleep(1000+200*j);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(IdleAnimation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                    ScaleTransition scaleTrans = new ScaleTransition();
                    scaleTrans.setToY(1);
                    scaleTrans.setFromY(2);
                    scaleTrans.setDuration(Duration.millis(1000));
                    scaleTrans.setNode(rects.get(j));
                    scaleTrans.play();
            }
        };
        
        Timer timer = new Timer(true);
        timer.schedule(animationTask, 0);
        }
    }
    
}
