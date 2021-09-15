/*
 * This file has been made by Joey Koster.
 * Code may be used in any form, but I'm kindly
 * asking to note my name.
 */
package com.blueblazes13.senjoassist.model;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author joeyk
 */
public class SecretModel {
    
    // Saving purposes only!
    private String spotifySecret;
    private String spotifyId;
    private String hueApiKey;
    
    // Statics containing secrets
    private static String staticSpotifySecret;
    private static String staticSpotifyId;
    private static String staticHueApiKey;
    
    
    public SecretModel() {
    }
    
    
    /**
     * Get the spotify secret
     * 
     * @return string containing the spotify secret
     */
    public static String getSpotifySecret() {
        return staticSpotifySecret;
    }
    
    
    /**
     * Get the spotify id
     * 
     * @return string containing the spotify id
     */
    public static String getSpotifyId() {
        return staticSpotifyId;
    }
    
    
    /**
     * Get the Hue api key
     * 
     * @return string containing the Hue api key
     */
    public static String getHueApiKey() {
        return staticHueApiKey;
    }
    
    
    public static void save() {
        final SecretModel model = new SecretModel();
        model.spotifyId = SecretModel.staticSpotifyId;
        model.spotifySecret = SecretModel.staticSpotifySecret;
        model.hueApiKey = SecretModel.staticHueApiKey;
        
        try (FileWriter fileWriter = new FileWriter("secrets.txt")) {
            Gson gsonConverter = new Gson();
            
            String convertedFile = gsonConverter.toJson(model, SecretModel.class);
            fileWriter.write(convertedFile);
        } catch(IOException ex) {
            System.err.println("Error while trying to save secrets");
            ex.printStackTrace();
        }
    }
    
    
    public static void load() {
        try (FileReader fileReader = new FileReader("secrets.txt")) {
            Gson gsonConverter = new Gson();
            
            SecretModel model = gsonConverter.fromJson(fileReader, SecretModel.class);
            SecretModel.staticSpotifyId = model.spotifyId;
            SecretModel.staticSpotifySecret = model.spotifySecret;
            SecretModel.staticHueApiKey = model.hueApiKey;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
