package com.example.homeworkday10;

import android.content.Context;
import android.util.Log;

/**
 * SingletonActivity 中使用这里的 static 的函数，会引用 SingletonActivity 的 this，
 * 导致 SingletonActivity 结束后不会销毁
 */

public class Singleton {

    public static final String TAG = "MySingleton";

    private static volatile Singleton instance;
    private Context context;

    public Singleton() {
        Log.d(TAG, "Singleton: ");
    }

    private Singleton(Context context) {
        this.context = context;
        Log.d(TAG, "Singleton: ");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d(TAG, "finalize: ");
    }

    public static Singleton getInstance(Context context) {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null)
                    instance = new Singleton(context);
            }
        }
        return instance;
    }

    public String getText() {
        return "Singleton Text";
    }
}
