/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blueblazes13.senjoassist.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 *
 * @author joeyk
 */
public class TimeView extends Region {
    
    private Label timeLabel;
    private TimeView model;
    
    public TimeView() {
        this.model = this;
        
        this.timeLabel = new Label();
        this.timeLabel.setFont(Font.font("Segoe UI", 70));
        this.timeLabel.setAlignment(Pos.CENTER);
        this.timeLabel.setPrefSize(190, 100);
        this.timeLabel.setTextFill(Color.web("#e4e4e4"));
        this.getChildren().add(this.timeLabel);
        
        this.start();
    }
    
    
    private void update() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.timeLabel.setText(formatter.format(time));
    }
    
    
    private void start() {
        TimerTask updater = new TimerTask() {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(model::update);
                    
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }    
        };
        
        Timer timer = new Timer(true);
        timer.schedule(updater, 0);
    }
    
}
