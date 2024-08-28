package com.example.homeworkday10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * 课堂练习5
 * HandlerMessageActivity
 * handler Message 需要在onDestroy()中removeCallbacksAndMessages
 */

public class HandlerMessageActivity extends AppCompatActivity {

    public static final String TAG = "MyHandlerMessageActivity";
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.layout_button_textview);

        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                // 执行任务
                System.out.println("Task executed");
                // 重新安排任务
                handler.postDelayed(this, 1000);
            }
        };

        // 启动任务
        handler.post(runnable);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        // 移除所有挂起的消息和回调
        handler.removeCallbacksAndMessages(null);
    }
}