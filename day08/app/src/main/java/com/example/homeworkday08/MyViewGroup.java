package com.example.homeworkday08;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;

import androidx.customview.widget.ViewDragHelper;

public class MyViewGroup extends ViewGroup {

    private static final int VIEW_MARGIN = 40;
    public static final String TAG = "MyViewGroupTAG";

    // 记录触摸的初始位置
    private float downX, downY;

    // 当前拖动的视图
    private View draggedView;

    // 用于检测缩放手势
    private ScaleGestureDetector scaleDetector;

    // 记录当前的缩放比例
    private float scaleFactor = 1.0f;

    public MyViewGroup(Context context) {
        super(context);
        init(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        // 初始化 ScaleGestureDetector
        scaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int rowWidth = 0;
        int totalHeight = 0;
        int rowMaxHeight = 0;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();

            if (rowWidth + width > maxWidth) {
                totalHeight += rowMaxHeight + VIEW_MARGIN;
                rowWidth = width;
                rowMaxHeight = height;
            } else {
                rowWidth += width + VIEW_MARGIN;
                rowMaxHeight = Math.max(rowMaxHeight, height);
            }
        }
        totalHeight += rowMaxHeight + VIEW_MARGIN;

        setMeasuredDimension(maxWidth, totalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int row = 0;
        int lengthX = l;
        int lengthY = t;
        int rowMaxHeight = 0;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();

            if (lengthX + width > r) {
                lengthX = l;
                lengthY += rowMaxHeight + VIEW_MARGIN;
                row++;
            }

            child.layout(lengthX, lengthY, lengthX + width, lengthY + height);

            lengthX += width + VIEW_MARGIN;
            rowMaxHeight = Math.max(rowMaxHeight, height);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                downY = ev.getY();
                // 找到触摸点下的子视图
                draggedView = findChildViewUnder(downX, downY);
                break;
        }
        return draggedView != null; // 如果触摸到了子视图，则拦截事件
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (draggedView == null) return false;

        // 处理缩放手势
        scaleDetector.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();
                int offsetX = (int) (moveX - downX);
                int offsetY = (int) (moveY - downY);
                draggedView.offsetLeftAndRight(offsetX);
                draggedView.offsetTopAndBottom(offsetY);
                downX = moveX;
                downY = moveY;
                invalidate(); // 重新绘制视图
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // 在手指抬起或取消时，尝试与目标位置的视图交换位置
                View targetView = findChildViewUnder(event.getX(), event.getY());
                if (targetView != null && targetView != draggedView) {
                    swapViews(draggedView, targetView);
                }
                draggedView = null;
                requestLayout(); // 重新布局
                break;
        }
        return true;
    }

    private View findChildViewUnder(float x, float y) {
        int count = getChildCount();
        for (int i = count - 1; i >= 0; i--) { // 从上到下遍历子视图
            View child = getChildAt(i);
            if (child == draggedView) continue;
            if (x >= child.getLeft() && x <= child.getRight() &&
                    y >= child.getTop() && y <= child.getBottom()) {
                return child;
            }
        }
        return null;
    }

    private void swapViews(View view1, View view2) {
        int index1 = indexOfChild(view1);
        int index2 = indexOfChild(view2);

        if (!(index1 >= 0 && index2 >= 0)) return;

        if (index1 > index2) { swapViews(view2, view1); return; }

        // 交换两个视图的位置
        removeViewAt(index1);
        removeViewAt(index2 - 1);

        addView(view2, index1);
        addView(view1, index2);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f)); // 限制缩放范围

            // 应用缩放到 draggedView
            draggedView.setScaleX(scaleFactor);
            draggedView.setScaleY(scaleFactor);
            invalidate();
            return true;
        }
    }
}
