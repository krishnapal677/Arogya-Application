/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arogyaapp.utility;

import java.util.Base64;

/**
 *
 * @author vishal chandravanshi
 */
public class PasswordEncryption {
    public static void main(String[] args) {
        Base64.Encoder en=Base64.getEncoder();
        String pwd="Admin";
        byte[]arr=pwd.getBytes();
        String encpwd=en.encodeToString(arr);
        System.out.println(encpwd);
    }
    public static  String getEncryption(char[] array)
    {
        String str=new String (array);
        Base64.Encoder en=Base64.getEncoder();
       
        byte[]arr=str.getBytes();
        String encpwd=en.encodeToString(arr);
        
        return encpwd;
    }
    
    /*
      public static String getEncryptedPassword(String password) 
    {    
        Base64.Encoder encoder = Base64.getEncoder();  
 
        return encoder.encodeToString(password.getBytes());  
    }  
    */
       
}
