package com.example.homeworkday08;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;
import android.graphics.Color;

public class LinedEditText extends AppCompatEditText {

    private Rect mRect;
    private Paint mPaint;

    public LinedEditText(Context context) {
        this(context, null);
    }

    public LinedEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public LinedEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#7FFF01"));  // 修正颜色解析
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int count = getLineCount();
        Rect r = mRect;
        Paint paint = mPaint;

        for (int i = 0; i < count; i++) {
            int baseline = getLineBounds(i, r);
            canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
        }

        super.onDraw(canvas);
    }
}
