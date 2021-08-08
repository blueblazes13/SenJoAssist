/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blueblazes13.senjoassist.controls;

import java.util.concurrent.Callable;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author joeyk
 */
public class SwitchButton extends Region {
    
    private boolean enabled;
    private Circle button;
    private Rectangle base;
    
    
    public SwitchButton() {
        this.enabled = false;
        update();
        
        this.setDisabled();
    }
    
    
    public void setOnMouseClick(Callable<Void> func) {
        this.setOnMouseClicked((MouseEvent me)->{
            onTouch(me);
            if (func != null) {
                try {
                    func.call();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    
    
    private void onTouch(MouseEvent me) {
        this.toggle();
    }
    
    
    private void update() {
        this.base = new Rectangle(60, 30);
        base.setArcHeight(30);
        base.setArcWidth(30);
        
        this.button = new Circle(15);
        
        this.getChildren().addAll(base, button);
        
        this.setOnMouseClick(null);
    }
    
    
    private void setDisabled() {
        Path path = new Path();
        path.getElements().add(new MoveTo(45, 15));
        path.getElements().add(new CubicCurveTo(45, 15, 40, 15, 15, 15));
        
        PathTransition trans = new PathTransition();
        trans.setDuration(Duration.millis(200));
        trans.setPath(path);
        trans.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        trans.setAutoReverse(false);
        trans.setCycleCount(1);
        trans.setNode(this.button);
        trans.setOnFinished((ActionEvent ae)->{
            this.base.setFill(Color.web("#EEEEEE"));
            this.button.setFill(Color.web("#fab143"));
        });
        trans.play();
        
        FillTransition fillTrans = new FillTransition();
        fillTrans.setDuration(Duration.millis(200));
        fillTrans.setAutoReverse(false);
        fillTrans.setCycleCount(1);
        fillTrans.setFromValue(Color.web("#EEEEEE"));
        fillTrans.setToValue(Color.web("#fab143"));
        fillTrans.setShape(this.button);
        fillTrans.play();
        
        fillTrans = new FillTransition();
        fillTrans.setDuration(Duration.millis(200));
        fillTrans.setAutoReverse(false);
        fillTrans.setCycleCount(1);
        fillTrans.setFromValue(Color.web("#fab143"));
        fillTrans.setToValue(Color.web("#EEEEEE"));
        fillTrans.setShape(this.base);
        fillTrans.play();
    }
    
    
    private void setEnabled() {
        Path path = new Path();
        path.getElements().add(new MoveTo(15, 15));
        path.getElements().add(new CubicCurveTo(15, 15, 20, 15, 45, 15));
        
        PathTransition trans = new PathTransition();
        trans.setDuration(Duration.millis(200));
        trans.setPath(path);
        trans.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        trans.setAutoReverse(false);
        trans.setCycleCount(1);
        trans.setNode(this.button);
        trans.setOnFinished((ActionEvent ae)->{
            this.base.setFill(Color.web("#fab143"));
            this.button.setFill(Color.web("#EEEEEE"));
        });
        trans.play();
        
        FillTransition fillTrans = new FillTransition();
        fillTrans.setDuration(Duration.millis(200));
        fillTrans.setAutoReverse(false);
        fillTrans.setCycleCount(1);
        fillTrans.setFromValue(Color.web("#fab143"));
        fillTrans.setToValue(Color.web("#EEEEEE"));
        fillTrans.setShape(this.button);
        fillTrans.play();
        
        fillTrans = new FillTransition();
        fillTrans.setDuration(Duration.millis(200));
        fillTrans.setAutoReverse(false);
        fillTrans.setCycleCount(1);
        fillTrans.setFromValue(Color.web("#EEEEEE"));
        fillTrans.setToValue(Color.web("#fab143"));
        fillTrans.setShape(this.base);
        fillTrans.play();
    }
    
    
    private void toggle() {
        if (this.enabled) {
            this.setDisabled();
        } else {
            this.setEnabled();
        }
        this.enabled = !this.enabled;
    }
    
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
}
