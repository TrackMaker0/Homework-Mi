package com.example.homeworkday06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_adapter);

        // 设置Adapter
        RecyclerView mRecyclerView = findViewById(R.id.mRecyclerView);
        DemoAdapter adapter = new DemoAdapter(new ArrayList<Integer>());
        mRecyclerView.setAdapter(adapter);

        // 数据
        List<Integer> list = new ArrayList<>();

        List<Integer> mIcons = new ArrayList<>();

        mIcons.add(R.drawable.icon1);
        mIcons.add(R.drawable.icon2);
        mIcons.add(R.drawable.icon3);
        mIcons.add(R.drawable.icon4);
        mIcons.add(R.drawable.icon5);
        mIcons.add(R.drawable.icon6);
        mIcons.add(R.drawable.icon7);
        mIcons.add(R.drawable.icon8);
        mIcons.add(R.drawable.icon9);
        mIcons.add(R.drawable.icon10);

        // SwipeRefresh
        SwipeRefreshLayout swipeRefreshView = findViewById(R.id.swipe_refresh_layout);

        swipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        Random random = new Random();
                        for (int i = 0; i < 100; i++) {
                            list.add(mIcons.get(random.nextInt(10)));
                        }
                        adapter.notifyDataSetChanged();
                        swipeRefreshView.setRefreshing(false);
                    }
                }, 500);
            }
        });

        adapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void onLoadMore() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Random random = new Random();
                        for (int i = 0; i < 100; i++) {
                            list.add(mIcons.get(random.nextInt(10)));
                        }
                        adapter.notifyDataSetChanged();
                        adapter.getLoadMoreModule().loadMoreComplete();
                    }
                }, 500);
            }
        });

        // 设置新的数据方法
        adapter.setNewData(list);
    }
}