package com.example.homeworkday07;

import android.animation.TypeEvaluator;

/*
 * 线性估值器
 */

public class LinearEvaluator implements TypeEvaluator<Float> {
    @Override
    public Float evaluate(float fraction, Float startValue, Float endValue) {

        return startValue + fraction * (endValue - startValue);
    }
}

