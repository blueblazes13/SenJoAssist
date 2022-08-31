/*
 * This file has been made by Joey Koster.
 * Code may be used in any form, but I'm kindly
 * asking to note my name.
 */
package com.blueblazes13.senjoassist.model;

import io.github.zeroone3010.yahueapi.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.blueblazes13.senjoassist.App;
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
    boolean isConnected = false;

    // TODO: Make ip changable thru settings menu
    final static String BRIDGE_IP = "192.168.0.227";
    
    
    public HueModel() {
        connectBridgeASync();
    }
    
    
    /**
     * Asynchronously tries to connect to the local Hue Bridge
     */
    private void connectBridgeASync() {
        TimerTask task = new TimerTask(){
            
            @Override
            public void run() {
                while (!connectBridge(BRIDGE_IP)) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
                isConnected = true;
            }
        };

        Timer timer = new Timer(true);
        timer.schedule(task, 0);
    }


    /**
     * Tries to connect to the local Hue Bridge, based on the given ip adress
     * 
     * @param ip The ip adress of the Bridge
     * @return true if the connection has been established. False if connecting failed.
     */
    private boolean connectBridge(String ip) {
        try {
            this.hue = new Hue(ip, SecretModel.getHueApiKey());
            this.room = (Room) hue.getRooms().toArray()[0];
            return true;
        } catch (Exception ex) {
            System.err.println("No bridge found!");
            return false;
        }
    }


    public boolean isConnected() {
        return this.isConnected;
    }
    
    
    public Room getRoom() {
        return this.room;
    }

    public LightView getView() {
        return new LightView(this);
    }

    public LightView getSymbol() {
        return new LightView(this);
    }


    // TODO: Move event from LightView to this function
    public void onMouseClick(MouseEvent me) {
        try {
            App.setRoot("HueMenuFXML");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
