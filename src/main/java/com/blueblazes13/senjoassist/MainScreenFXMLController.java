package com.blueblazes13.senjoassist;

import com.blueblazes13.senjoassist.view.Cube;
import com.blueblazes13.senjoassist.model.HueModel;
import com.blueblazes13.senjoassist.model.RadioModel;
import com.blueblazes13.senjoassist.model.SpotifyModel;
import com.blueblazes13.senjoassist.view.IdleAnimation;
import com.blueblazes13.senjoassist.view.LightView;
import com.blueblazes13.senjoassist.view.TimeView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainScreenFXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane apMainScreen;
    
    @FXML
    void initialize() {
        update();
        new HueModel();
        new SpotifyModel();
    }
    
    
    private void update() {
        this.apMainScreen.getChildren().clear();
        
        // Show radio controls
        RadioModel radio = new RadioModel();
        radio.getRadioView().setLayoutX(400);
        radio.getRadioView().setLayoutY(380);
        radio.setRadio("https://playerservices.streamtheworld.com/api/livestream-redirect/RADIO538.mp3?dist=538_mobileweb&ttag=talpa_consent:1&gdpr=1&gdpr_consent=CPGcNTHPLkPI0ADABBNLBpCsAP_AAEJAAAAAGCQGQAKgAXABAADIAIkATABPADEAG4APwAgABGAClAFcAO8AhABFoCOAI6AS4AnYBWQD9gIpAXmAvYBggGCQFgAKgAXABAADIAIgATQAngBiAD8AIwAUoArgB3gEIAIsARwAnYBWQD9gIpAXmAwQAsJAKAAqACAAGQARAAmABPAFKAO8AjgC8x0AwACoAIAAZABEACYAE8AMQApQB3gEWAI4AvMcADAAuAK4AhBSAYABUAEAAMgAiABMACeAGIAUoA7wCLAEcAXmUABAAXAJ2SAAgAXJQBwAiABMADEAKUAd4BHAF5kIAgATAAxADvAI4GAAgCuFQAwAmAEcAXmMgBgBMAI4AvM.e8AAAAAAA4AA");
        this.apMainScreen.getChildren().add(radio.getRadioView());
        
//        Cube cube = new Cube();
//        cube.setPos(512, 400);
//        cube.startMove();
        
        // Show time
        TimeView time = new TimeView();
        time.setLayoutX(512-190/2);
        time.setLayoutY(100);
        
        // Show settings
        FileInputStream stream = null;
        try {
            stream = new FileInputStream("settings2.png");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        Image image = new Image(stream);
        ImageView settings = new ImageView(image);
        settings.setFitHeight(50);
        settings.setFitWidth(50);
        settings.setLayoutX(850);
        settings.setLayoutY(500);
        
        // Show light buttons
        LightView lightMenu = new LightView(new HueModel());
        lightMenu.setLayoutX(30);
        lightMenu.setLayoutY(520);
        
        
        this.apMainScreen.getChildren().addAll(time, settings, lightMenu);
    }
}
