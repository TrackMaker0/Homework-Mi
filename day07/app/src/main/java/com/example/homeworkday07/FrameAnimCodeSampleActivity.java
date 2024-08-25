package com.example.homeworkday07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class FrameAnimCodeSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_anim_code_sample);

        ImageView runImageView = findViewById(R.id.iv_run_anim);
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.setOneShot(false);
        animationDrawable.addFrame(ContextCompat.getDrawable(this, R.drawable.run1), 200);
        animationDrawable.addFrame(ContextCompat.getDrawable(this, R.drawable.run2), 200);
        animationDrawable.addFrame(ContextCompat.getDrawable(this, R.drawable.run3), 200);
        animationDrawable.addFrame(ContextCompat.getDrawable(this, R.drawable.run4), 200);
        animationDrawable.addFrame(ContextCompat.getDrawable(this, R.drawable.run5), 200);
        runImageView.setImageDrawable(animationDrawable);
        animationDrawable.start();
    }
}