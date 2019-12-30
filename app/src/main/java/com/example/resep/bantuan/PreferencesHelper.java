package com.example.resep.bantuan;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.resep.model.User;

public class PreferencesHelper {
    private SharedPreferences sharedPreferences;
    private final String PREFERENCE_NAME = "shared_preferences";
    private final String ID_USER = "id";
    private final String LOGIN = "login";
    private final String TOKEN = "token";
    private final String NAME = "name";
    private final String EMAIL = "email";
    private final String FCM = "fcm_token";

    public PreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void setLogin(boolean login){
        sharedPreferences.edit().putBoolean(LOGIN, login).apply();
    }

    public void setFCM(String fcm_token){
        sharedPreferences.edit().putString(FCM, fcm_token).apply();
    }

    public String getFCM(){
        return sharedPreferences.getString(FCM, "");
    }

    public void setID_USER(String id_user){
        sharedPreferences.edit().putString(ID_USER, id_user).apply();
    }

    public String getID_USER(){
        return  sharedPreferences.getString(ID_USER, "");
    }

    public boolean getLogin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void setToken(String token){
        sharedPreferences.edit().putString(TOKEN, token).apply();
    }

    public String getToken(){
        return sharedPreferences.getString(TOKEN, "");
    }

    public void setName(String name){
        sharedPreferences.edit().putString(NAME, name).apply();
    }

    public String getName(){
        return sharedPreferences.getString(NAME, "");
    }

    public void setEmail(String email){
        sharedPreferences.edit().putString(EMAIL, email).apply();
    }

    public String getEmail(){
        return sharedPreferences.getString(EMAIL, "");
    }

    public void setUserLogin(User user){
        setLogin(true);
        setID_USER(user.getId());
        setToken(user.getToken());
        setName(user.getName());
        setEmail(user.getEmail());
        setFCM(user.getFcm_token());
    }

    public void logout(){
        sharedPreferences.edit()
                .clear()
                .apply();
    }
}
