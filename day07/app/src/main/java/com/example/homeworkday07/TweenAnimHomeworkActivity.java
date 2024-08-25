package com.example.homeworkday07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class TweenAnimHomeworkActivity extends AppCompatActivity {

    public static final int REPEAT_COUNT = 2;
    public static final int ANIM_DURATION = 2000;
    public static final String TAG = "TweenHomework";
    private AnimationSet animationSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_anim_homework);

        ImageView robotImageView = findViewById(R.id.iv_robot);
        // 基于View中心点放大1.5倍
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1.5f, 1f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        // 逆时针旋转720度
        RotateAnimation rotateAnimation = new RotateAnimation(0, -720,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        // 由不透明变为透明度0.8
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.8f);

        scaleAnimation.setRepeatCount(REPEAT_COUNT);
        rotateAnimation.setRepeatCount(REPEAT_COUNT);
        alphaAnimation.setRepeatCount(REPEAT_COUNT);

        animationSet = new AnimationSet(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(ANIM_DURATION);

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            private int counter;

            @Override
            public void onAnimationStart(Animation animation) {
                counter = 1;
                Log.d(TAG, "onAnimationStart: animation start");
                Toast.makeText(TweenAnimHomeworkActivity.this,
                        "Run times : " + counter, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG, "onAnimationEnd: animation end");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d(TAG, "onAnimationRepeat: animation repeat " + counter + "times");
                counter++;
                Toast.makeText(TweenAnimHomeworkActivity.this,
                        "Run times : " + counter, Toast.LENGTH_SHORT).show();
            }
        });

        robotImageView.startAnimation(animationSet);

        findViewById(R.id.btn_replay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                robotImageView.clearAnimation(); // 清除当前动画
                robotImageView.startAnimation(animationSet); // 重新启动动画
            }
        });
    }
}