package com.blueblazes13.senjoassist;

import com.blueblazes13.senjoassist.controls.SwitchButton;
import com.blueblazes13.senjoassist.model.HueModel;

import io.github.zeroone3010.yahueapi.Light;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HueMenuFXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane apMainScreen;

    @FXML
    private Button btnBack;
    
    HueModel hue;

    @FXML
    void initialize() {
        this.hue = new HueModel();
        update();

        btnBack.setOnAction((ActionEvent ae) -> {
            try {
                App.setRoot(App.MAIN_MENU);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    
    
    private void update() {
        this.apMainScreen.getChildren().forEach((Object child) -> {if (child != this.btnBack) this.apMainScreen.getChildren().remove(child);});
        //if (!this.hue.isConnected()) return;
        
        int i = 0;
        while (i < 20) {
        //for (Light light: hue.getRoom().getLights()) {
            SwitchButton button = new SwitchButton();
            button.setLayoutX(20);
            button.setLayoutY(20 + 40*i);

            i++;

            // TODO: Try if light.setState(button.isEnabled()) works
            // button.setOnMouseClick(() -> {
            //     if (button.isEnabled()) {
            //         light.turnOn();
            //     } else {
            //         light.turnOff();
            //     }
            //     return null;
            // });

            Label title = new Label("TEST_LAMP");
            title.setLayoutX(80);
            title.setLayoutY(20 + 40*i);
            title.setAlignment(Pos.CENTER_LEFT);
            title.setFont(Font.font("Segoe UI", 16));
            title.setTextFill(Color.web("#e4e4e4"));
            
            this.apMainScreen.getChildren().addAll(button, title);
            // i++;
        }
    }
}