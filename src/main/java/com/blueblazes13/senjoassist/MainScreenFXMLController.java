package com.blueblazes13.senjoassist;

import com.blueblazes13.senjoassist.view.Cube;
import com.blueblazes13.senjoassist.controls.SwitchButton;
import com.blueblazes13.senjoassist.model.HueModel;
import com.blueblazes13.senjoassist.view.LightView;
import com.blueblazes13.senjoassist.view.TimeView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.AmbientLight;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;

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
    }
    
    
    private void update() {
        this.apMainScreen.getChildren().clear();
        
        Cube cube = new Cube();
        cube.setPos(512, 400);
        cube.startMove();
        
        TimeView time = new TimeView();
        time.setLayoutX(512-190/2);
        time.setLayoutY(100);
        
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
        settings.setLayoutX(800);
        settings.setLayoutY(500);
        
        LightView lightMenu = new LightView(new HueModel());
        lightMenu.setLayoutX(30);
        lightMenu.setLayoutY(520);
        
        this.apMainScreen.getChildren().addAll(cube, time, settings, lightMenu);
    }
}
