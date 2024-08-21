package com.example.homeworkday03;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class FragmentTongxinActivity extends AppCompatActivity implements DemoFragment.TopPageActionListener {

    private DemoFragment2 demoFragment2;

    @Override
    public void onTopPageAction() {
        if (demoFragment2 == null) {
            demoFragment2 = new DemoFragment2();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_tongxin_container_view2, demoFragment2, null)
                    .commit();
        }
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(GenerateRandomColor());
        findViewById(R.id.fragment_tongxin_container_view2).setBackground(colorDrawable);
    }

    private int GenerateRandomColor() {
        Random rand = new Random();
        int a = 255;
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        return Color.argb(a, r, g, b);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tongxin);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_tongxin_container_view, new DemoFragment(), null)
                .commit();

        findViewById(R.id.btn_changecolor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable colorDrawable = new ColorDrawable();
                colorDrawable.setColor(GenerateRandomColor());
                findViewById(R.id.fragment_tongxin_container_view).setBackground(colorDrawable);
            }
        });
    }
}