package com.example.homeworkday09;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpGsonActivity extends Activity {

    private Button mButton;
    private TextView mTextView;
    private OkHttpClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_gson);

        mButton = findViewById(R.id.button_fetch_data);
        mTextView = findViewById(R.id.text_view_result);

        // 初始化 OkHttpClient
        mClient = new OkHttpClient();

        // 设置按钮点击事件
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData();
            }
        });
    }

    private void fetchData() {
        // 创建 Request 对象
        Request request = new Request.Builder()
//                .url("https://jsonplaceholder.typicode.com/todos/1")
                .url("https://api.github.com/")
                .build();

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    TodoItem todoItem = new Gson().fromJson(responseBody, TodoItem.class);

                    // 更新 UI 需要在主线程中执行
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText(todoItem.toString());
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText("Failed to fetch data: " + response.code());
                        }
                    });
                }
            }
        });
    }

    // 定义一个简单的 Java 类来表示 JSON 数据
    static class TodoItem {
        private int id;
        private String title;
        private boolean completed;

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public boolean isCompleted() {
            return completed;
        }

        @Override
        public String toString() {
            return "Todo Item Details:\nID: " + id + "\nTitle: " + title + "\nCompleted: " + completed;
        }
    }
}