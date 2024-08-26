package com.example.homeworkday08;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TwoFingerTouchView extends View {

    private Paint paint;
    private boolean isTwoFingersDown;
    private int circleX, circleY;

    public TwoFingerTouchView(Context context) {
        this(context, null);
    }

    public TwoFingerTouchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TwoFingerTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        isTwoFingersDown = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isTwoFingersDown) {
            canvas.drawCircle(circleX, circleY, 50, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                // 第一根手指按下
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                // 第二根手指按下
                isTwoFingersDown = true;
                circleX = (int) event.getX(0);
                circleY = (int) event.getY(0);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                // 检查是否有两个手指同时移动
                if (event.getPointerCount() == 2) {
                    circleX = (int) event.getX(0);
                    circleY = (int) event.getY(0);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                // 取消手势
                // 一根手指抬起
                isTwoFingersDown = false;
                invalidate();
                break;
        }
        return true;
    }
}