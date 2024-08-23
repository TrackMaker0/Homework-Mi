package com.example.homeworkday05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        setContentView(R.layout.layout_exercise1);
//        setContentView(R.layout.layout_exercise2);
//        setContentView(R.layout.layout_exercise3);
//        setContentView(R.layout.layout_exercise4);
//        setContentView(R.layout.layout_exercise5);
//        setContentView(R.layout.merge_layout);
        setContentView(R.layout.layout_homework);

//        Exercise4();
        Homework();

    }

    private void Homework() {

        ArrayList<View> mServiceViews = new ArrayList<>();
        mServiceViews.add(findViewById(R.id.item_service1));// 重置密码
        mServiceViews.add(findViewById(R.id.item_service2));// 账号申诉
        mServiceViews.add(findViewById(R.id.item_service3));// 冻结账号
        mServiceViews.add(findViewById(R.id.item_service4));// 解冻账号
        mServiceViews.add(findViewById(R.id.item_service5));// 解封账号
        mServiceViews.add(findViewById(R.id.item_service6));// 注销账号

        ArrayList<Integer> mServiceIcons = new ArrayList<>();
        mServiceIcons.add(R.drawable.ic_service1);
        mServiceIcons.add(R.drawable.ic_service2);
        mServiceIcons.add(R.drawable.ic_service3);
        mServiceIcons.add(R.drawable.ic_service4);
        mServiceIcons.add(R.drawable.ic_service5);
        mServiceIcons.add(R.drawable.ic_service6);

        List<String> tempList = Arrays.asList("重置密码", "账号申诉", "冻结账号", "解冻账号", "解封账号", "注销账号");
        ArrayList<String> mServiceNames = new ArrayList<>(tempList);

        for (int i = 0; i < mServiceViews.size(); i++) {
            View service = mServiceViews.get(i);
            ImageView icon = service.findViewById(R.id.ivService);
            TextView name = service.findViewById(R.id.tvService);
            icon.setImageResource(mServiceIcons.get(i));
            name.setText(mServiceNames.get(i));
        }

        TextView category1 = findViewById(R.id.help_category1).findViewById(R.id.tvCategoryTitle);
        TextView category2 = findViewById(R.id.help_category2).findViewById(R.id.tvCategoryTitle);
        TextView category3 = findViewById(R.id.help_category3).findViewById(R.id.tvCategoryTitle);
        TextView category4 = findViewById(R.id.help_category4).findViewById(R.id.tvCategoryTitle);
        category1.setText("忘记账号了，该如何找回");
        category2.setText("忘记密码了，如何重置密码");
        category3.setText("手机号停用了，如何登陆或换绑手机号？");
        category4.setText("申述不通过怎么办？");

        TextView tvCommonQuestions = findViewById(R.id.tvCommonQuestions);
        tvCommonQuestions.setBackgroundResource(R.drawable.rounded_azure_background);
        tvCommonQuestions.setTextColor(ContextCompat.getColor(this, R.color.blue));
    }

    private void Exercise4() {
        CheckBox checkBox = findViewById(R.id.checkBox);
        SpannableStringBuilder spannableString = new SpannableStringBuilder();

        String text1 = "已阅读并同意 ";
        String agreement = "《用户协议》";
        String privacyPolicy = "《隐私政策》";

        // 添加普通文本
        spannableString.append(text1);

        // 添加用户协议链接
        int start = spannableString.length();
        spannableString.append(agreement);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ADD8E6")),
                start, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // 打开用户协议链接
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com"));
                widget.getContext().startActivity(intent);
            }
        }, start, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 添加隐私政策链接
        start = spannableString.length();
        spannableString.append(privacyPolicy);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ADD8E6")),
                start, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // 打开隐私政策链接
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com"));
                widget.getContext().startActivity(intent);
            }
        }, start, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置SpannableStringBuilder到TextView
        checkBox.setText(spannableString);
        checkBox.setMovementMethod(LinkMovementMethod.getInstance());

        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com")));
            }
        });

        findViewById(R.id.btn_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btn_loginpassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com")));
            }
        });

        findViewById(R.id.btn_help).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com")));
            }
        });

        findViewById(R.id.btn_help2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com")));
            }
        });
    }
}