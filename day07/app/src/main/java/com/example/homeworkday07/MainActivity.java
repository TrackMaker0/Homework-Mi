package com.example.homeworkday07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        startActivity(new Intent(MainActivity.this, FrameAnimXmlSampleActivity.class));
//        startActivity(new Intent(MainActivity.this, FrameAnimCodeSampleActivity.class));
//        startActivity(new Intent(MainActivity.this, TweenAnimTranslateXmlActivity.class));
//        startActivity(new Intent(MainActivity.this, TweenAnimTranslateCodeActivity.class));
//        startActivity(new Intent(MainActivity.this, TweenAnimScaleXmlActivity.class));
//        startActivity(new Intent(MainActivity.this, TweenAnimScaleCodeActivity.class));
//        startActivity(new Intent(MainActivity.this, TweenAnimSetXmlActivity.class));
//        startActivity(new Intent(MainActivity.this, TweenAnimSetCodeActivity.class));
//        startActivity(new Intent(MainActivity.this, PropertyAnimRotateXmlActivity.class));
//        startActivity(new Intent(MainActivity.this, PropertyAnimRotateCodeActivity.class));
//        startActivity(new Intent(MainActivity.this, ValueAnimActivity.class));
//        startActivity(new Intent(MainActivity.this, ViewPropertyAnimActivity.class));

        findViewById(R.id.btn_homework1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TweenAnimHomeworkActivity.class));
            }
        });

        findViewById(R.id.btn_homework2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PropertyAnimHomeworkActivity.class));
            }
        });
    }
}