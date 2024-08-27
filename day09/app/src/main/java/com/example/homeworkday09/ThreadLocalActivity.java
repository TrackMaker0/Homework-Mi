package com.example.homeworkday09;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalActivity extends Activity {

    private static final String TAG = "MyThreadLocalActivity";
    private static final ThreadLocal<String> threadNameHolder = new ThreadLocal<>();

    ThreadLocal<Integer> value = new ThreadLocal<Integer>();
    private int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_local);

        // 提交多个任务
        runTasksInParallel();
    }

    private void runTasksInParallel() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            int taskId = i;
            executorService.submit(() -> {
                a++;
                // 设置线程局部变量的值
                threadNameHolder.set("Task " + taskId + " - " + Thread.currentThread().getName());

                // 执行一些操作
                doWork(taskId);
            });
        }

        // 关闭线程池
        executorService.shutdown();
    }

    private void doWork(int taskId) {
        try {
            // 模拟耗时操作
            Thread.sleep(1000); // 暂停1秒
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

        // 访问线程局部变量
        String threadName = threadNameHolder.get();
        Log.d(TAG, "Thread " + threadName + ": Task " + taskId + " completed.");
        Log.d(TAG, "Thread " + threadName + ": Task " + taskId + " completed." + "a value" + a);
        Log.d(TAG, "Thread " + threadName + ": Task " + taskId + " completed." + "threadlocal value" + value.get());
    }
}