/*
 * This file has been made by Joey Koster.
 * Code may be used in any form, but I'm kindly
 * asking to note my name.
 */
package com.blueblazes13.senjoassist.model;

import io.github.zeroone3010.yahueapi.*;

/**
 *
 * @author joeyk
 */
public class HueModel {
    
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
        final String apiKey = "thnv8-4zg725ocX7psf9IRWI7-qzkPJTigu9CaMD";
        
        this.hue = new Hue(bridgeIp, apiKey);
        System.out.println(hue.getRooms());
        this.room = (Room) this.hue.getRooms().toArray()[0];
        System.out.println(this.room.getLights());
    }
    
    
    public Room getRoom() {
        return this.room;
    }
    
}
