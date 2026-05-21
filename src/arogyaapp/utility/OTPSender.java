package arogyaapp.utility;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;

public class OTPSender implements Sender {

    public boolean send(String number, String data) throws Exception {
        Unirest.setTimeouts(0, 0);
        // Remove +91 if present, keep only 10 digits
        String mobNo = number.replaceAll("\\+91", "").trim();
        int otp = Integer.parseInt(data);
        // ✅ Correct URL with your valid API key
        String url = "https://2factor.in/API/V1/7a0b6a70-1bbb-11f1-bcb0-0200cd936042/SMS/" 
                     + mobNo + "/" + otp + "/OTP1";
        System.out.println("Sending OTP to: " + mobNo);
        System.out.println("OTP is: " + otp);
        GetRequest gr = Unirest.get(url);
        HttpResponse<String> response = gr.asString();
        String result = response.getBody();
        System.out.println("2Factor Response: " + result);
        return result.contains("Success");
    }
}