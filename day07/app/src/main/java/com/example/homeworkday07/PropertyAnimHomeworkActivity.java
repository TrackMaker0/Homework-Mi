package com.example.homeworkday07;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PropertyAnimHomeworkActivity extends AppCompatActivity {

    private AnimatorSet animatorSet; // 用于重播动画的AnimatorSet

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim_homework);

        TextView propertyAnimTv = findViewById(R.id.tv_property);

        // 围绕X轴旋转360度，持续1000ms
        ObjectAnimator rotateAnimator =
                ObjectAnimator.ofFloat(propertyAnimTv, View.ROTATION_X, 0, 360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setInterpolator(new SquareInterpolator());
        rotateAnimator.setEvaluator(new LinearEvaluator());

        // 向右移动120px，持续1000ms
        ObjectAnimator translateAnimator =
                ObjectAnimator.ofFloat(propertyAnimTv, View.TRANSLATION_X, 0, 120);
        translateAnimator.setDuration(1000);
        translateAnimator.setInterpolator(new SqrtInterpolator());
        translateAnimator.setEvaluator(new LinearEvaluator());

        // 从不透明变成透明度0.5，持续500ms
        ObjectAnimator alphaAnimator =
                ObjectAnimator.ofFloat(propertyAnimTv, View.ALPHA, 1f, 0.5f);
        alphaAnimator.setDuration(500);

        animatorSet = new AnimatorSet();

        // 同时执行旋转和平移动画
        animatorSet.playTogether(rotateAnimator, translateAnimator);

        // 然后顺序执行透明度变化
        animatorSet.play(alphaAnimator).after(rotateAnimator);

        // 启动动画
        animatorSet.start();

        // 设置按钮点击事件监听器
        findViewById(R.id.btn_replay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 重新播放动画
                if (animatorSet != null) {
                    animatorSet.cancel(); // 取消当前正在进行的动画
                    animatorSet.start(); // 重新启动动画
                }
            }
        });
    }
}