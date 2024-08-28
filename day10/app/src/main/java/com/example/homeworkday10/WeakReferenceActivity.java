package com.example.homeworkday10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;

/**
 * 课堂练习2
 * 匿名内部类隐式持有外部类的引用
 * 解决方案：使用 Reference
 */

public class WeakReferenceActivity extends AppCompatActivity {

    private WeakReference<WeakReferenceActivity> activityReference;
    public static final String TAG = "MyWeakReferenceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.layout_button_textview);

        Button button = findViewById(R.id.button);

        // 匿名内部类，有内存泄露风险
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 处理按钮点击事件
//                doSomething();
//            }
//        });

        // 解决方案：使用Reference
        activityReference = new WeakReference<>(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeakReferenceActivity activity = activityReference.get();
                if (activity != null) {
                    doSomething();
                }
            }
        });
    }

    private void doSomething() {
        // ...
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