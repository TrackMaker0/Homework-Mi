package com.example.homeworkday07;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.Queue;

public class StackedToast {
    private Context context;
    private WindowManager windowManager;
    private Queue<View> toastQueue;
    private Handler handler;
    private static final int TOAST_DURATION = 20000; // Toast显示时长
    private static final int TOAST_HEIGHT = 150; // 每个Toast的高度，用于堆叠

    public StackedToast(Context context) {
        this.context = context;
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        toastQueue = new LinkedList<>();
        handler = new Handler();
    }

    public void showToast(String message) {
        // 创建自定义Toast视图
        View toastView = LayoutInflater.from(context).inflate(R.layout.custom_toast_layout, null);
        TextView textView = toastView.findViewById(R.id.toast_text);
        textView.setText(message);

        // 设置背景色
        toastView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_blue_light));

        // 设置布局参数
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = 1;
        params.windowAnimations = android.R.style.Animation_Toast;
        params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;

        // 显示Toast
        windowManager.addView(toastView, params);
        toastQueue.add(toastView);

        // 更新所有Toast的位置
        updateToastPositions();

        // 设置显示时长
        handler.postDelayed(() -> removeToast(toastView), TOAST_DURATION);
    }

    private void updateToastPositions() {
        for (View toastView : toastQueue) {
            WindowManager.LayoutParams params = (WindowManager.LayoutParams) toastView.getLayoutParams();
            params.y += TOAST_HEIGHT; // 更新Y轴位置
            windowManager.updateViewLayout(toastView, params);
        }
    }

    private void removeToast(View toastView) {
        if (toastQueue.contains(toastView)) {
            windowManager.removeView(toastView);
            toastQueue.remove(toastView);
        }
    }
}


