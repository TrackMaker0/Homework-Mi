package com.example.homeworkday06;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ViewPagerAdapter viewPagerAdapter;
//    private List<Fragment> mFragments;  //存放视图的数组
//    private Fragment fragment1,fragment2,fragment3;
//    private PagerAdapter mPagerAdapter;//适配器

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        // 初始化Adapter
        InitAdapter();
        // 设置点击事件
        setClickListener();
    }

    private void InitAdapter() {
        mViewPager = findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);
    }

    private void setClickListener() {
        findViewById(R.id.radioButton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(PAGE_ONE);
            }
        });

        findViewById(R.id.radioButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(PAGE_TWO);
            }
        });

        findViewById(R.id.radioButton3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(PAGE_THREE);
            }
        });
    }
}