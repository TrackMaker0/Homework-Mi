package com.example.homeworkday04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class RotateImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_image);

        SeekBar rotateBar = findViewById(R.id.set_rotate);
        ImageView imageView = findViewById(R.id.image);
        TextView rotateText = findViewById(R.id.rotate_text);

        rotateBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                imageView.setPivotX(imageView.getWidth() / 2);
                imageView.setPivotY(imageView.getHeight() / 2);
                float alpha = progress / 360.0f;
                imageView.setRotation(progress);
                imageView.setAlpha(alpha);
                rotateText.setText("设置旋转角度：" + progress + "  设置透明度：" + alpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}