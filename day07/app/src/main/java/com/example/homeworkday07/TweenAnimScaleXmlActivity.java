package com.example.homeworkday07;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class TweenAnimScaleXmlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_anim_scale_xml);

        ImageView robotImageView = findViewById(R.id.iv_robot);

        final Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        animation1.setDuration(1000);
        animation1.setFillAfter(true); // 保持动画结束状态

        final Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.anim_scale_back);
        animation2.setDuration(1000);
        animation2.setFillAfter(true); // 保持动画结束状态

        final Handler handler = new Handler();

        // 定义动画监听器
        final Animation.AnimationListener listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // 动画开始时的操作（如果需要）
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 在动画结束时，设置200毫秒的延时后启动下一个动画
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (animation == animation1) {
                            robotImageView.startAnimation(animation2);
                        } else {
                            robotImageView.startAnimation(animation1);
                        }
                    }
                }, 0); // 设置间隔为200毫秒
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // 动画重复时的操作（如果需要）
            }
        };

        // 设置动画监听器
        animation1.setAnimationListener(listener);
        animation2.setAnimationListener(listener);

        // 启动第一个动画
        robotImageView.startAnimation(animation1);
    }
}