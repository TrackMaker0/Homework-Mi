package com.example.homeworkday07;

import android.animation.TypeEvaluator;

/*
 * 弹性估值器
 */
public class BounceEvaluator implements TypeEvaluator<Float> {
    @Override
    public Float evaluate(float fraction, Float startValue, Float endValue) {
        float amplitude = 1.0f;
        float frequency = 10.0f;
        return startValue + (endValue - startValue) * (float) (Math.sin(frequency * fraction) * Math.pow(2, -10 * fraction) + amplitude);
    }
}
