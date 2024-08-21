package com.example.homeworkday03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeworkActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<View> mViews;  //存放视图的数组
    private View view1,view2,view3;
    private PagerAdapter mPagerAdapter;//适配器

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);

        mViewPager=findViewById(R.id.viewpager);// 实例化viewpager控件

        LayoutInflater inflater = getLayoutInflater();//获取布局对象管理
        view1=inflater.inflate(R.layout.layout1,null);//实例化view
        view2=inflater.inflate(R.layout.layout2,null);
        view3=inflater.inflate(R.layout.layout3,null);

        mViews=new ArrayList<View>();//将要显示的布局存放到list数组
        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);

        //实例化一个PagerAdapter的适配器
        mPagerAdapter = new PagerAdapter() {

            @Override   //返回要滑动的VIew的个数
            public int getCount() {

                return mViews.size();
            }

            @Override  //来判断pager的一个view是否和instantiateItem方法返回的object有关联
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

                return view==object;
            }

            @Override  //从当前container中删除指定位置（position）的View;
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

                container.removeView(mViews.get(position));
            }

            @NonNull
            @Override  //第一：将当前视图添加到container中，第二：返回当前View
            public Object instantiateItem(@NonNull ViewGroup container, int position) {

                container.addView(mViews.get(position));
                return mViews.get(position);
            }
        };

        mViewPager.setAdapter(mPagerAdapter);//设置适配器

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