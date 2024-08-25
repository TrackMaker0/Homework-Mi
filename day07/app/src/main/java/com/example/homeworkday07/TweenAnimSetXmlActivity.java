package com.example.homeworkday07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class TweenAnimSetXmlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_anim_set_xml);

        ImageView robotImageView = findViewById(R.id.iv_robot);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_set);
        robotImageView.startAnimation(animation);
    }
}