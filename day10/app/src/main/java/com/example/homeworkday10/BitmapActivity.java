package com.example.homeworkday10;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

/**
 * 课堂练习7
 * BitmapActivity
 * Bitmap 导致内存泄漏
 * 解决方法：在 Activity 的 onDestroy 回收 Bitmap 内存，并进行销毁
 */

public class BitmapActivity extends AppCompatActivity {

    public static final String TAG = "MyBitmapActivity";
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_bitmap);

        // 创建一个大的 Bitmap 对象
        bitmap = Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_8888);
        // 使用 bitmap 进行某些操作
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        // 如果不调用 bitmap.recycle(), bitmap 对象不会被释放
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle(); // 回收 Bitmap 内存
            bitmap = null; // 清除对 Bitmap 的引用
        }
    }
}