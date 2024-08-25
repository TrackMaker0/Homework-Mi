package com.example.homeworkday07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ValueAnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_anim);

        TextView propertyAnimTv = findViewById(R.id.tv_property);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                propertyAnimTv.setRotationX(currentValue * 360f);
                propertyAnimTv.setTranslationX(currentValue * 100);
            }
        });

        valueAnimator.setStartDelay(2000);
        valueAnimator.setDuration(1000);
        valueAnimator.start();
    }
}