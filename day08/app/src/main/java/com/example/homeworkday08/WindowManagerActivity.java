package com.example.homeworkday08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class WindowManagerActivity extends AppCompatActivity {


    private WindowManager windowManager;
    private View floatingView;

    private static final int OVERLAY_PERMISSION_REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_manager);

        // 检查并请求悬浮窗权限
        if (Settings.canDrawOverlays(this)) {
            // 如果已经有权限，继续执行代码
            setupFloatingView();
        } else {
            // 请求权限
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQUEST_CODE);
        }
    }

    private void setupFloatingView() {
        // 获取 WindowManager
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // 创建一个简单的视图，作为悬浮视图
        floatingView = LayoutInflater.from(this).inflate(R.layout.layout_textview, null);

        // 设置布局参数
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, // 系统级别的窗口类型
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,      // 窗口不会获取焦点
                PixelFormat.TRANSLUCENT                              // 背景透明
        );

        // 设置初始位置
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;

        // 将视图添加到窗口中
        windowManager.addView(floatingView, params);

        // 设置拖动功能
        floatingView.setOnTouchListener(new View.OnTouchListener() {
            private int lastX, lastY;
            private int initialX, initialY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 记录初始位置
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        initialX = params.x;
                        initialY = params.y;
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        // 计算移动距离
                        int deltaX = (int) event.getRawX() - lastX;
                        int deltaY = (int) event.getRawY() - lastY;
                        params.x = initialX + deltaX;
                        params.y = initialY + deltaY;

                        // 更新视图位置
                        windowManager.updateViewLayout(floatingView, params);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在 Activity 销毁时移除悬浮视图，避免内存泄漏
        if (floatingView != null) {
            windowManager.removeView(floatingView);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OVERLAY_PERMISSION_REQUEST_CODE) {
            if (Settings.canDrawOverlays(this)) {
                // 用户授予权限后设置悬浮视图
                setupFloatingView();
            } else {
                // 用户拒绝授予权限
                Toast.makeText(this, "无法显示悬浮视图，缺少权限", Toast.LENGTH_SHORT).show();
            }
        }
    }
}