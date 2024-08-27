package com.example.homeworkday09;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MyCameraActivity
        findViewById(R.id.btn_mycamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyCameraActivity.class));
            }
        });

        // ClassCameraActivity
        findViewById(R.id.btn_classcamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ClassCameraActivity.class));
            }
        });

        // MyGetPhoneMainActivity
        findViewById(R.id.btn_getphone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyGetPhoneMainActivity.class));
            }
        });

        // AsynchronousNetworkRequestsActivity
        findViewById(R.id.btn_netrequest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AsynchronousNetworkRequestsActivity.class));
            }
        });

        // OkhttpCaptchaActivity
        findViewById(R.id.btn_okhttpcaptcha).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OkhttpCaptchaActivity.class));
            }
        });

        // OkhttpGsonActivity
        findViewById(R.id.btn_okhttpgson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OkhttpGsonActivity.class));
            }
        });

        // ThreadLocalActivity
        findViewById(R.id.btn_threadlocal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThreadLocalActivity.class));
            }
        });

        // Homework1
        findViewById(R.id.btn_homework1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyCameraActivity.class));
            }
        });

        // Homework2
        findViewById(R.id.btn_homework2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AsynchronousNetworkRequestsActivity.class));
            }
        });
    }
}
