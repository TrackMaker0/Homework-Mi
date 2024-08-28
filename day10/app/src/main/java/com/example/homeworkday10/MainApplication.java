package com.example.homeworkday10;

import android.app.Application;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new MyANRWatchDog(1000).start();
    }
}
