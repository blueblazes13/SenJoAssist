/*
 * This file has been made by Joey Koster.
 * Code may be used in any form, but I'm kindly
 * asking to note my name.
 */
package com.blueblazes13.senjoassist.model;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import java.io.IOException;
import java.net.URI;
import org.apache.hc.core5.http.ParseException;

/**
 *
 * @author joeyk
 */
public class SpotifyModel {
    
    public SpotifyModel() {
        String code = "";
        
        URI redirectUri = SpotifyHttpManager.makeUri("joeykoster.be:25562");
        
        SpotifyApi.Builder spotifyBuilder = new SpotifyApi.Builder();
        spotifyBuilder.setClientId(SecretModel.getSpotifyId());
        spotifyBuilder.setClientSecret(SecretModel.getSpotifySecret());
        spotifyBuilder.setRedirectUri(redirectUri);
        
        SpotifyApi spotifyApi = spotifyBuilder.build();
        
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
        
        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code).build();
        AuthorizationCodeUriRequest req = spotifyApi.authorizationCodeUri().build();
        req.execute();
        
        try {
            ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
            System.out.println(clientCredentials.getExpiresIn());
            AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();
            System.out.println(authorizationCodeCredentials.getAccessToken());
            
            // Set access and refresh token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
            
            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }
//    
//    // For all requests an access token is needed
//    SpotifyApi spotifyApi = new SpotifyApi.Builder()
//        .setAccessToken("taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk")
//        .build();
//
//    // Create a request object with the optional parameter "market"
//    final GetSomethingRequest getSomethingRequest = spotifyApi.geta("qKRpDADUKrFeKhFHDMdfcu")
//        .market(CountryCode.SE)
//        .build();
//
//    void getSomething_Sync() {
//      try {
//        // Execute the request synchronous
//        final Something something = getSomethingRequest.execute();
//
//        // Print something's name
//        System.out.println("Name: " + something.getName());
//      } catch (Exception e) {
//        System.out.println("Something went wrong!\n" + e.getMessage());
//      }
//    }
//
//    void getSomething_Async() {
//      try {
//        // Execute the request asynchronous
//        final Future<Something> somethingFuture = getSomethingRequest.executeAsync();
//
//        // Do other things...
//
//        // Wait for the request to complete
//        final Something something = somethingFuture.get();
//
//        // Print something's name
//        System.out.println("Name: " + something.getName());
//      } catch (Exception e) {
//        System.out.println("Something went wrong!\n" + e.getMessage());
//      }
//    }
//    
}
