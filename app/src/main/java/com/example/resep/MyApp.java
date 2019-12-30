package com.example.resep;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.facebook.stetho.Stetho;

public class MyApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

//    public void onCreate(){
//        super.onCreate();
//        Stetho.initializeWithDefaults(this);
//        MultiDex.install(this);
//    }
}
