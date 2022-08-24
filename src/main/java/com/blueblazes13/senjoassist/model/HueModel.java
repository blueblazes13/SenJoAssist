/*
 * This file has been made by Joey Koster.
 * Code may be used in any form, but I'm kindly
 * asking to note my name.
 */
package com.blueblazes13.senjoassist.model;

import io.github.zeroone3010.yahueapi.*;
import javafx.scene.input.MouseEvent;

import java.util.Timer;
import java.util.TimerTask;

import com.blueblazes13.senjoassist.interfaces.MenuItem;
import com.blueblazes13.senjoassist.view.LightView;

/**
 *
 * @author joeyk
 */
public class HueModel implements MenuItem {
    
    /*
    [{"success":{"username":"thnv8-4zg725ocX7psf9IRWI7-qzkPJTigu9CaMD"}}]
    Store this API key for future use: thnv8-4zg725ocX7psf9IRWI7-qzkPJTigu9CaMD
    */
    
    Hue hue;
    Room room;
    
    
    public HueModel() {
        setup();
    }
    
    
    private void setup() {
        final String bridgeIp = "192.168.0.227";
        final String appName = "SenJoAssist";
        final String apiKey = SecretModel.getHueApiKey();
        
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                try {
                    hue = new Hue(bridgeIp, apiKey);
                    System.out.println(hue.getRooms());
                    room = (Room) hue.getRooms().toArray()[0];
                    System.out.println(room.getLights());
                } catch (Exception ex) {
                    System.err.println("No bridge found!");
                }
            }
        };
        Timer timer = new Timer(true);
        timer.schedule(task, 0);
    }
    
    
    public Room getRoom() {
        return this.room;
    }

    public LightView getView() {
        return new LightView(this);
    }

    // TODO: Move event from LightView to this function
    public void onMouseClick(MouseEvent me) {
        
    }

    
}
