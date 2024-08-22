package com.example.homeworkday04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    @Deprecated
    private void UsernameAutoFill() {
        EditText editText = findViewById(R.id.edit_username);
        String defaultText = "请输入用户名";

        // 设置默认文本
        editText.setText(defaultText);

        // 监听焦点变化
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 获得焦点时清空文本
                    if (editText.getText().toString().equals(defaultText)) {
                        editText.setText("");
                    }
                } else {
                    // 失去焦点时，如果内容为空，设置为默认文本
                    if (editText.getText().toString().isEmpty()) {
                        editText.setText(defaultText);
                    }
                }
            }
        });
    }
}