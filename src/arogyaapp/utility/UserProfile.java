/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arogyaapp.utility;

/**
 *
 * @author vishal chandravanshi
 */
public class UserProfile {
    
    public static String userName;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UserProfile.userName = userName;
    }

    public static String getUserType() {
        return userType;
    }

    public static void setUserType(String userType) {
        UserProfile.userType = userType;
    }

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        UserProfile.userID = userID;
    }
    public static String userType;
    public static String userID ;
   
}
