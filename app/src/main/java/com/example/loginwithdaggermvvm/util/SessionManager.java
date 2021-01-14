package com.example.loginwithdaggermvvm.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.loginwithdaggermvvm.view.LoginActivity;

import java.util.HashMap;

public class SessionManager {
    public static final String KEY_TOKEN = "token";
    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "username";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_USER_ID = "userId";
    // Sharedpref file name
    private static final String PREF_NAME = "iGoTelDb";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    Editor editor;
    // Context
    Context context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Session Manager Class
    SessionManager session;


    // Constructor
    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.apply();
    }

    /**
     * Create login session
     */
    public void createLoginSession(String username, String userId, String phone, String token) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing username in pref
        editor.putString(KEY_NAME, username);

        // Storing userId in pref
        editor.putString(KEY_USER_ID, userId);

        // Storing phone in pref
        editor.putString(KEY_PHONE, phone);

        // Storing token in pref
        editor.putString(KEY_TOKEN, token);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            // Staring Login Activity
            context.startActivity(i);
            ((Activity) context).finish();
        }

    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();

        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_TOKEN, pref.getString(KEY_TOKEN, null));
        user.put(KEY_USER_ID, pref.getString(KEY_USER_ID, null));
        user.put(KEY_PHONE, pref.getString(KEY_PHONE, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Staring Login Activity
        context.startActivity(i);
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public boolean checkConnection() {

//        return ConnectivityReceiver.isConnected();
        return true;
    }


}