package com.example.homeworkday10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ANRActivity
 */

public class ANRActivity extends AppCompatActivity {

    public static final String TAG = "MyANRActivity";

    private final Object mObj_Synchronized = new Object();
    private final Object mObj_ReentrantLock = new Object();
    private final Object mObj_Child1 = new Object();
    private final Object mObj_Child2 = new Object();
    private boolean isComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anractivity);

//        Test_Synchronized();

//        Test_ReentrantLock();

//        ChildThreadDeadlock();

    }

    private void ChildThreadDeadlock() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: First Thread Start");
                synchronized (mObj_Child1) {
                    try {
                        Thread.sleep(6 * 1000);
                        synchronized (mObj_Child2) {
                            Log.d(TAG, "run: First Thread ing...");
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Log.d(TAG, "run: First Thread Exit");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: Second Thread Start");
                synchronized (mObj_Child2) {
                    try {
                        Thread.sleep(6 * 1000);
                        synchronized (mObj_Child1) {
                            Log.d(TAG, "run: Second Thread ing...");
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Log.d(TAG, "run: Second Thread Exit");
            }
        }).start();
    }

    private void Test_ReentrantLock() {
        findViewById(R.id.btn_reentrantlock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synchronized (mObj_ReentrantLock) {
                    Log.i(TAG, "ReentrantLock onClick: 超时锁定机制, 完成情况" + isComplete);
                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mObj_ReentrantLock) {
                    ReentrantLock lock = new ReentrantLock();
                    try {
                        lock.tryLock(3000, TimeUnit.MICROSECONDS);
                        isComplete = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    private void Test_Synchronized() {
        findViewById(R.id.btn_synchronized).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synchronized (mObj_Synchronized) {
                    Log.i(TAG, "onClick: 死锁导致ANR, 完成情况" + isComplete);
                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mObj_Synchronized) {
                    try {
                        Thread.sleep(30 * 1000);
                        isComplete = true;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}