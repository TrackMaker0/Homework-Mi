package com.example.homeworkday06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);

        Glide.with(this)
//                .load(R.drawable.miku)
//                .load(Uri.parse("https://i.pximg.net/img-original/img/2019/03/10/00/35/03/73597952_p0.jpg"))
                .load(Uri.parse("https://i1.hdslb.com/bfs/archive/e5a57842312adf795350cee87bc0363144452b84.jpg"))
                .into(imageView);

        // EventBus
//        startActivity(new Intent(MainActivity.this, AActivity.class));

        // BaseRecyclerViewAdapterHelper
//        startActivity(new Intent(MainActivity.this, HomeAdapterActivity.class));

        // Homework
        startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));
    }
}