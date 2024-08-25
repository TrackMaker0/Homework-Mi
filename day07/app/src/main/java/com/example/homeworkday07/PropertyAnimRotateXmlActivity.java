package com.example.homeworkday07;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.TextView;

public class PropertyAnimRotateXmlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim_rotate_xml);

        TextView propertyAnimTv = findViewById(R.id.tv_property);

        ObjectAnimator animator =
                (ObjectAnimator) AnimatorInflater.loadAnimator(
                        this, R.animator.animator_rotate_x);
        animator.setTarget(propertyAnimTv);
        animator.start();
    }
}