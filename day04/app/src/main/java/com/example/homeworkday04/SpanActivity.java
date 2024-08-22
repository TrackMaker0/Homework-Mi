package com.example.homeworkday04;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.View;

public class SpanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span);


        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        SpannableString spanString = new SpannableString(str);
        // 设置背景色
        spanString.setSpan(new BackgroundColorSpan(Color.argb(255,255,0,0)), 3, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        // 设置前置色
        spanString.setSpan(new BackgroundColorSpan(Color.argb(255,0,255,0)), 3, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        findViewById(R.id.btn_backtomain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}