package com.example.homeworkday10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 课堂练习3
 * InnerClassActivity
 * 内部类隐式持有外部类引用，导致内存泄漏
 * 解决方法：将内部类改为静态内部类
 */

public class InnerClassActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "ExampleActivity3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.layout_button_textview);

        Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);

        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        InnerClass innerClass = new InnerClass();
        innerClass.test();
        finish();
    }

    // 解决方法：将内部类改为静态内部类
    private static class InnerClass {
        public void test() {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Log.i(TAG, "run: 内部类中持有");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
            thread.start();
        }
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
    }
}