package com.example.homeworkday07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.TextView;

public class ViewPropertyAnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property_anim);

        TextView propertyAnimTv = findViewById(R.id.tv_property);

        propertyAnimTv.animate()
                .rotationX(360)
                .translationX(100)
                .setDuration(1000)
                .setStartDelay(1000)
                .start();
    }
}