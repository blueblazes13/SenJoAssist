/*
 * This file has been made by Joey Koster.
 * Code may be used in any form, but I'm kindly
 * asking to note my name.
 */
package com.blueblazes13.senjoassist.view;

import com.blueblazes13.senjoassist.model.RadioModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

/**
 *
 * @author joeyk
 */
public class RadioView extends Region {
    
    RadioModel model;
    
    Image playImage = null;
    Image pauseImage = null;
    Image listImage = null;
    
    
    
    public RadioView(RadioModel model) {
        this.model = model;
        
        
        this.setOnMouseClicked(this::onClick);
        init();
    }
    
    private void init() {
        FileInputStream playStream = null;
        FileInputStream pauseStream = null;
        FileInputStream listStream = null;
        
        try {
            playStream = new FileInputStream("playButton.png");
            pauseStream = new FileInputStream("pauseButton.png");
            listStream = new FileInputStream("listButton.png");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        this.playImage = new Image(playStream);
        this.pauseImage = new Image(pauseStream);
        this.listImage = new Image(listStream);
        
        update();
    }
    
    
    private void update() {
        this.getChildren().clear();
        ImageView view;
        
        if (this.model.isPlaying()) {
            view = new ImageView(this.pauseImage);
        } else {
            view = new ImageView(this.playImage);
        }
        
        view.setFitHeight(50);
        view.setFitWidth(50);
        this.getChildren().add(view);
    }
    
    
    private void onClick(MouseEvent me) {
        if (this.model.isPlaying()) {
            this.model.stop();
        } else {
            this.model.play();
        }
        
        this.update();
    }
    
}
