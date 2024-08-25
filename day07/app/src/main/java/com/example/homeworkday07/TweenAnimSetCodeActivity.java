package com.example.homeworkday07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class TweenAnimSetCodeActivity extends AppCompatActivity {

    public static final int REPEAT_COUNT = 30;
    public static final int ANIM_DURATION = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_anim_set_code);

        ImageView robotImageView = findViewById(R.id.iv_robot);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1.5f, 1f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);

        AnimationSet animationSet = new AnimationSet(true);
        scaleAnimation.setRepeatCount(REPEAT_COUNT);
        rotateAnimation.setRepeatCount(REPEAT_COUNT);
        alphaAnimation.setRepeatCount(REPEAT_COUNT);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(ANIM_DURATION);

        StackedToast stackedToast = new StackedToast(TweenAnimSetCodeActivity.this);

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            private int counter = 1;

            @Override
            public void onAnimationStart(Animation animation) {
                stackedToast.showToast("Repeat times : " + counter);
//                Toast.makeText(TweenAnimSetCodeActivity.this,
//                        "Repeat times : " + counter, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                counter++;
                stackedToast.showToast("Repeat times : " + counter);
//                Toast.makeText(TweenAnimSetCodeActivity.this,
//                        "Repeat times : " + counter, Toast.LENGTH_SHORT).show();
            }
        });

        robotImageView.startAnimation(animationSet);
    }

}