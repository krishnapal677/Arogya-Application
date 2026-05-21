/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package arogyaapp.utility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class SmsSender implements Sender{
    
public boolean send(String number, String message) throws Exception{
        

    // This URL is used for sending messages
    String myURI = "https://api.bulksms.com/v1/messages";
    StringBuilder result = null;
    
    // change these values to match your own account
    String myUsername = "vishalpa";
      String myPassword = "VishalPatel1";

    
    number = "91"+number;
    String myData = "{to: \'"+number+"\', body: \""+message+"\"}";
        System.out.println("mydata:"+myData);

    // build the request based on the supplied settings
    URL url = new URL(myURI);
    HttpURLConnection request = (HttpURLConnection) url.openConnection();
    request.setDoOutput(true);

    // supply the credentials
    String authStr = myUsername + ":" + myPassword;
    String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
    request.setRequestProperty("Authorization", "Basic " + authEncoded);

    // we want to use HTTP POST
    request.setRequestMethod("POST");
    request.setRequestProperty( "Content-Type", "application/json");

    // write the data to the request
    OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
    out.write(myData);
    out.close();

    // try ... catch to handle errors nicely
    try {
      // make the call to the API
      result = new StringBuilder();
      InputStream response = request.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(response));
      String replyText;
      while ((replyText = in.readLine()) != null) {
        System.out.println(replyText);
        result.append(replyText);
        
      }
        System.out.println("out of loop");
      in.close();
    } catch (IOException ex) {
        result = new StringBuilder();
      System.out.println("An error occurred:" + ex.getMessage());
      BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
      // print the detail that comes with the error
      String replyText;
      while ((replyText = in.readLine()) != null) {
        System.out.println(replyText);
        result.append(replyText);
      }
      in.close();
    }
    request.disconnect();
        System.out.println("now returning");
    //return result.toString().contains("SENT");
   return true;   
} 
}
*/
package arogyaapp.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class SmsSender implements Sender {

    public boolean send(String number, String message) throws Exception {

        String myURI = "https://api.bulksms.com/v1/messages";
        StringBuilder result = new StringBuilder();

        // CHANGE 1: Purane username/password ki jagah screenshot wale Token Id aur Secret dalein
        String myTokenId = "919F5894C33E4E3AB41FFBB91E6DDF41-02-D"; 
        String myTokenSecret = "nowXeveAiavq2SQlMIs3RrxmpxcwA"; 

        // CHANGE 2: Number format international hona chahiye (e.g., +91...)
        if (!number.startsWith("+")) {
            number = "+" + (number.startsWith("91") ? "" : "91") + number;
        }

        // CHANGE 3: JSON format ko sahi karein (keys "to" aur "body" hamesha double quotes mein honi chahiye)
        String myData = "{\"to\": \"" + number + "\", \"body\": \"" + message + "\"}";
        System.out.println("Payload: " + myData);

        URL url = new URL(myURI);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setDoOutput(true);
        request.setRequestMethod("POST");
        request.setRequestProperty("Content-Type", "application/json");

        // Authentication logic
        String authStr = myTokenId + ":" + myTokenSecret;
        String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
        request.setRequestProperty("Authorization", "Basic " + authEncoded);

        // Write data to request
        try (OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream())) {
            out.write(myData);
        }

        // Handling response
        try {
            int statusCode = request.getResponseCode();
            System.out.println("Response Status Code: " + statusCode);

            InputStream response = (statusCode >= 200 && statusCode < 300) 
                                   ? request.getInputStream() 
                                   : request.getErrorStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(response));
            String replyText;
            while ((replyText = in.readLine()) != null) {
                System.out.println(replyText);
                result.append(replyText);
            }
            in.close();

            // BulkSMS v1 success par HTTP 201 (Created) deta hai
            return (statusCode == 201);

        } catch (IOException ex) {
            System.out.println("An error occurred: " + ex.getMessage());
            return false;
        } finally {
            request.disconnect();
        }
    }
}