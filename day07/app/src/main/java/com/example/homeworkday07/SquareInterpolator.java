package com.example.homeworkday07;

import android.animation.TimeInterpolator;

public class SquareInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        return input * input;
    }
}
