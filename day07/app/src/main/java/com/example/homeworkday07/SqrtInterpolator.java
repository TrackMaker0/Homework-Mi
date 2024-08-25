package com.example.homeworkday07;

import android.animation.TimeInterpolator;

public class SqrtInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        return (float) Math.sqrt(input);
    }
}
