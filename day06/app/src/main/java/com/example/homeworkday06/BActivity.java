package com.example.homeworkday06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

public class BActivity extends AppCompatActivity {

    private int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bactivity);

        Button button = findViewById(R.id.btn_click);
        button.setOnClickListener(v -> {
            clickCount++;
            EventBus.getDefault().postSticky(new MessageEvent(clickCount));

            Log.d("MyEventBus", "Clicked : " + clickCount);
        });
    }
}