package com.hanhpk.chatapi;

import android.app.Application;

import com.hanhpk.chatapi.api.ApiClient;


public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ApiClient.getInstance();

    }

    public static App getInstance(){
        return instance;
    }
}
