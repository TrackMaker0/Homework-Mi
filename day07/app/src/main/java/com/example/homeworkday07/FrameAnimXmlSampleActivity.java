package com.example.homeworkday07;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class FrameAnimXmlSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_anim_xml_sample);

        ImageView runImageView = findViewById(R.id.iv_run_anim);
        runImageView.setImageResource(R.drawable.drawable_run_anim);

        AnimationDrawable animationDrawable =
                (AnimationDrawable) runImageView.getDrawable();
        animationDrawable.start();
    }
}