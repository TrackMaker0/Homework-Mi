package com.example.homeworkday07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class TweenAnimTranslateCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_anim_translate_code);

        ImageView robotImageView = findViewById(R.id.iv_robot);

        // 创建平移动画
        final TranslateAnimation translateAnimation1 =
                new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, -1f,
                        Animation.RELATIVE_TO_SELF, 1f);
        translateAnimation1.setDuration(1000);
        translateAnimation1.setFillAfter(true);

        // 创建平移动画
        final TranslateAnimation translateAnimation2 =
                new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 1f,
                        Animation.RELATIVE_TO_SELF, -1f);
        translateAnimation2.setDuration(1000);
        translateAnimation2.setFillAfter(true);

        // 创建放大动画
        final ScaleAnimation scaleAnimation1 =
                new ScaleAnimation(1f, 1.5f, // X轴从1倍放大到1.5倍
                        1f, 1.5f, // Y轴从1倍放大到1.5倍
                        Animation.RELATIVE_TO_SELF, 0.5f, // X轴缩放中心点
                        Animation.RELATIVE_TO_SELF, 0.5f); // Y轴缩放中心点
        scaleAnimation1.setDuration(1000);
        scaleAnimation1.setFillAfter(true);

        // 创建缩小动画
        final ScaleAnimation scaleAnimation2 =
                new ScaleAnimation(1.5f, 1f, // X轴从1.5倍缩小到1倍
                        1.5f, 1f, // Y轴从1.5倍缩小到1倍
                        Animation.RELATIVE_TO_SELF, 0.5f, // X轴缩放中心点
                        Animation.RELATIVE_TO_SELF, 0.5f); // Y轴缩放中心点
        scaleAnimation2.setDuration(1000);
        scaleAnimation2.setFillAfter(true);

        // 创建组合动画，将平移动画和放大动画结合
        AnimationSet animationSet1 = new AnimationSet(true);
        animationSet1.addAnimation(translateAnimation1);
        animationSet1.addAnimation(scaleAnimation1);

        // 创建组合动画，将平移动画和缩小动画结合
        AnimationSet animationSet2 = new AnimationSet(true);
        animationSet2.addAnimation(translateAnimation2);
        animationSet2.addAnimation(scaleAnimation2);

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
                        if (animation == animationSet1) {
                            robotImageView.startAnimation(animationSet2);
                        } else {
                            robotImageView.startAnimation(animationSet1);
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
        animationSet1.setAnimationListener(listener);
        animationSet2.setAnimationListener(listener);

        // 启动第一个动画
        robotImageView.startAnimation(animationSet1);

    }
}