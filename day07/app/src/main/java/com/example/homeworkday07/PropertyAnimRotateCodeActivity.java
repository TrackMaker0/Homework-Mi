package com.example.homeworkday07;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PropertyAnimRotateCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim_rotate_code);

        TextView propertyAnimTv = findViewById(R.id.tv_property);

        ObjectAnimator animator =
                ObjectAnimator.ofFloat(propertyAnimTv, View.ROTATION_X, 0f, 360f);
        animator.setDuration(1000);
        animator.setTarget(propertyAnimTv);
        animator.start();
    }
}