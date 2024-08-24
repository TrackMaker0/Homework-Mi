package com.example.homeworkday06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // 接收Intent
        Intent intent = getIntent();
        // 获取UI控件
        ImageView ivContent = findViewById(R.id.ivContentDetail);
        TextView tvContent = findViewById(R.id.tvContentDetail);
        Button loveImageBtn = findViewById(R.id.btn_loveIv);
        Button loveTextBtn = findViewById(R.id.btn_loveTv);
        // 通过键获取数据
        String imageUrl = intent.getStringExtra("ivContent");
        String textContent = intent.getStringExtra("tvContent");

        final boolean[] loveImage = {intent.getBooleanExtra("loveImage", false)};
        final boolean[] loveText = {intent.getBooleanExtra("loveText", false)};
        // 设置UI元素
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder) // 设置加载中的占位图
                .error(R.drawable.error) // 设置加载失败的占位图
                .into(ivContent);
        tvContent.setText(textContent);

        // 设置爱心
        if (loveImage[0]) {
            loveImageBtn.setBackgroundResource(R.drawable.ic_love);
        } else {
            loveImageBtn.setBackgroundResource(R.drawable.ic_unlove);
        }

        if (loveText[0]) {
            loveTextBtn.setBackgroundResource(R.drawable.ic_love);
        } else {
            loveTextBtn.setBackgroundResource(R.drawable.ic_unlove);
        }

        loveImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loveImage[0] = !loveImage[0];
                if (loveImage[0]) {
                    loveImageBtn.setBackgroundResource(R.drawable.ic_love);
                } else {
                    loveImageBtn.setBackgroundResource(R.drawable.ic_unlove);
                }
                EventBus.getDefault().postSticky(new MessageEvent(loveImage[0], loveText[0]));
            }
        });

        loveTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loveText[0] = !loveText[0];
                if (loveText[0]) {
                    loveTextBtn.setBackgroundResource(R.drawable.ic_love);
                } else {
                    loveTextBtn.setBackgroundResource(R.drawable.ic_unlove);
                }
                EventBus.getDefault().postSticky(new MessageEvent(loveImage[0], loveText[0]));
            }
        });
    }
}