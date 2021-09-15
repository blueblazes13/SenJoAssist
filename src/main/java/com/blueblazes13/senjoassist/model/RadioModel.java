/*
 * This file has been made by Joey Koster.
 * Code may be used in any form, but I'm kindly
 * asking to note my name.
 */
package com.blueblazes13.senjoassist.model;

import com.blueblazes13.senjoassist.view.RadioView;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author joeyk
 */
public class RadioModel {
    
    private URLConnection connection = null;
    private Player player = null;
    private Timer timer = null;
    private RadioView view;
    private String url;
    private HashMap<String, String> stations;
    
    public RadioModel() {
        this.view = new RadioView(this);
        
        this.stations = new HashMap<>();
        this.stations.put("Radio 538", "https://playerservices.streamtheworld.com/api/livestream-redirect/RADIO538.mp3?dist=538_mobileweb&ttag=talpa_consent:1&gdpr=1&gdpr_consent=CPGcNTHPLkPI0ADABBNLBpCsAP_AAEJAAAAAGCQGQAKgAXABAADIAIkATABPADEAG4APwAgABGAClAFcAO8AhABFoCOAI6AS4AnYBWQD9gIpAXmAvYBggGCQFgAKgAXABAADIAIgATQAngBiAD8AIwAUoArgB3gEIAIsARwAnYBWQD9gIpAXmAwQAsJAKAAqACAAGQARAAmABPAFKAO8AjgC8x0AwACoAIAAZABEACYAE8AMQApQB3gEWAI4AvMcADAAuAK4AhBSAYABUAEAAMgAiABMACeAGIAUoA7wCLAEcAXmUABAAXAJ2SAAgAXJQBwAiABMADEAKUAd4BHAF5kIAgATAAxADvAI4GAAgCuFQAwAmAEcAXmMgBgBMAI4AvM.e8AAAAAAA4AA");
    }
    
    
    public RadioView getRadioView() {
        return this.view;
    }
    
    
    public boolean isPlaying() {
        return this.timer != null;
    }
    
    
    public void setRadio(String url) {
        this.url = url;
        try {
            this.connection = new URL(this.url).openConnection();
        } catch (MalformedURLException ex) {
            Logger.getLogger(RadioModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RadioModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            connection.connect();
        } catch (IOException ex) {
            Logger.getLogger(RadioModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void play() {
        TimerTask radioTimer = new TimerTask() {
            @Override
            public void run() {
                setRadio(url);
                
                try {
                    player = new Player (connection.getInputStream());
                } catch (IOException ex) {
                    Logger.getLogger(RadioModel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JavaLayerException ex) {
                    Logger.getLogger(RadioModel.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    player.play();
                } catch (JavaLayerException ex) {
                    Logger.getLogger(RadioModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        this.timer = new Timer(true);
        this.timer.schedule(radioTimer, 0);
        
    }
    
    
    public void stop() {
        this.player.close();
        this.player = null;
        this.timer.cancel();
        this.timer = null;
    }
    
    
}
