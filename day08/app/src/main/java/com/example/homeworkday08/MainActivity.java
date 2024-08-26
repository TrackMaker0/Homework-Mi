package com.example.homeworkday08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 引用 LinedEditText
        LinedEditText linedEditText = findViewById(R.id.linedEditText);

        // 设置初始文本或其他属性
        linedEditText.setText("This is a custom LinedEditText.");

//        startActivity(new Intent(MainActivity.this, WindowManagerActivity.class));
        startActivity(new Intent(MainActivity.this, TwoFingerTouchActivity.class));
    }
}
