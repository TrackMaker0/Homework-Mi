package com.example.homeworkday09;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

public class OkhttpCaptchaActivity extends Activity {

    private TextView mTextView;
    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_captcha);

        mTextView = findViewById(R.id.textView);

        // 初始化 Handler 和 Runnable
        mHandler = new Handler(Looper.getMainLooper());
        mRunnable = new Runnable() {
            private int secondsLeft = 10; // 倒计时总秒数

            @Override
            public void run() {
                if (secondsLeft > 0) {
                    // 更新 UI
                    mTextView.setText("Time left: " + secondsLeft-- + " seconds");

                    // 重新调度 Runnable，延迟 1 秒
                    mHandler.postDelayed(this, 1000);
                } else {
                    // 倒计时结束时的处理
                    mTextView.setText("Countdown finished!");
                }
            }
        };

        // 开始倒计时
        mHandler.post(mRunnable);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消所有任务以防内存泄漏
        mHandler.removeCallbacks(mRunnable);
    }
}
