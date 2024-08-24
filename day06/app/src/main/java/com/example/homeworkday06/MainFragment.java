package com.example.homeworkday06;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import org.apache.commons.lang3.RandomStringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final int INIT_NUM = 10;
    public static final int ADD_NUM = 5;

    public static Button loveImageBtn;
    public static Button loveTextBtn;
    public static ContentItem contentItem;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(MessageEvent event) {
        // 更新 UI,获取Favorite状态,设置爱心
        if (event.loveImage) {
            loveImageBtn.setBackgroundResource(R.drawable.ic_love);
        } else {
            loveImageBtn.setBackgroundResource(R.drawable.ic_unlove);
        }
        if (event.loveText) {
            loveTextBtn.setBackgroundResource(R.drawable.ic_love);
        } else {
            loveTextBtn.setBackgroundResource(R.drawable.ic_unlove);
        }
        contentItem.setLoveImage(event.loveImage);
        contentItem.setLoveText(event.loveText);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // 设置Adapter
        RecyclerView mRecyclerView = view.findViewById(R.id.mRecyclerView);
        HomeworkAdapter adapter = new HomeworkAdapter(new ArrayList<ContentItem>());
        mRecyclerView.setAdapter(adapter);

        // MainContent对象
        List<ContentItem> list = new ArrayList<>();

        // 数据
        List<String> mTexts = new ArrayList<>();

        // 图像
        List<Integer> mImages = new ArrayList<>();

        getData(mTexts, mImages);

        List<String> imageUrls = Arrays.asList(
                "https://cdn.pixabay.com/photo/2024/07/23/13/08/moon-8915326_1280.png",
                "https://cdn.pixabay.com/photo/2024/08/03/02/32/drink-8941028_640.png",
                "https://cdn.pixabay.com/photo/2024/07/08/05/41/girl-8880144_640.png",
                "https://cdn.pixabay.com/photo/2023/08/17/08/55/ice-cream-8195933_640.png",
                "https://cdn.pixabay.com/photo/2019/10/27/07/37/watermelon-4580910_640.png",
                "https://cdn.pixabay.com/photo/2024/06/22/20/06/eiffel-tower-8846952_640.png",
                "https://cdn.pixabay.com/photo/2023/11/20/13/21/shape-8401083_640.png",
                "https://cdn.pixabay.com/photo/2023/12/07/11/04/girl-8435329_640.png",
                "https://cdn.pixabay.com/photo/2023/10/14/09/20/mountains-8314422_640.png",
                "https://cdn.pixabay.com/photo/2023/08/07/13/40/flowers-8175044_640.png"
        );

        // SwipeRefresh
        SwipeRefreshLayout swipeRefreshView = view.findViewById(R.id.swipe_refresh_layout);

        swipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent(list, imageUrls, mTexts, adapter, swipeRefreshView);
            }
        });

        adapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void onLoadMore() {
                loadMoreContent(imageUrls, mTexts, list, adapter);
            }
        });

        // 设置新的数据方法
        adapter.setNewData(list);

        // 初始调用
        refreshContent(list, imageUrls, mTexts, adapter, swipeRefreshView);

        return view;
    }

    private static void refreshContent(List<ContentItem> list, List<String> imageUrls, List<String> mTexts, HomeworkAdapter adapter, SwipeRefreshLayout swipeRefreshView) {
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void run() {
                list.clear();
                Random random = new Random();
                for (int i = 0; i < INIT_NUM; i++) {
                    ContentItem contentItem = new ContentItem();
//                            contentItem.setDrawableId(mImages.get(random.nextInt(10)));
                    contentItem.setImageUrl(imageUrls.get(random.nextInt(10)));
                    contentItem.setText(mTexts.get(random.nextInt(10)));
                    list.add(contentItem);
                }
                adapter.notifyDataSetChanged();
                swipeRefreshView.setRefreshing(false);
            }
        }, 500);
    }

    private static void loadMoreContent(List<String> imageUrls, List<String> mTexts, List<ContentItem> list, HomeworkAdapter adapter) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                for (int i = 0; i < ADD_NUM; i++) {
                    ContentItem contentItem = new ContentItem();
//                            contentItem.setDrawableId(mImages.get(random.nextInt(10)));
                    contentItem.setImageUrl(imageUrls.get(random.nextInt(10)));
                    contentItem.setText(mTexts.get(random.nextInt(10)));
                    list.add(contentItem);
                }
                adapter.notifyDataSetChanged();
                adapter.getLoadMoreModule().loadMoreComplete();
            }
        }, 500);
    }

    private static void getData(List<String> mTexts, List<Integer> mImages) {
        mTexts.add("秋风萧瑟，落叶随风舞，思绪纷飞中，心绪渐平静。");
        mTexts.add("青山隐隐，白云悠悠，水墨丹青绘，心中故乡梦。");
        mTexts.add("晴川历历汉阳树，芳草萋萋鹦鹉洲，岁月静好。");
        mTexts.add("月落乌啼霜满天，江枫渔火对愁眠，清秋无尽意。");
        mTexts.add("小桥流水人家，柳丝轻垂拂波光，静谧春日里。");
        mTexts.add("江南水乡，烟雨朦胧中，青瓦白墙入画来。");
        mTexts.add("青山绿水绕村郭，春风吹拂麦田香，心生悠然情。");
        mTexts.add("落霞与孤鹜齐飞，秋水共长天一色，画意诗情间。");
        mTexts.add("桃花流水窅然去，别有天地非人间，芳菲满春意。");
        mTexts.add("云卷云舒花自开，风轻雨细润心田，岁月静流淌。");

        mImages.add(R.drawable.icon1);
        mImages.add(R.drawable.icon2);
        mImages.add(R.drawable.icon3);
        mImages.add(R.drawable.icon4);
        mImages.add(R.drawable.icon5);
        mImages.add(R.drawable.icon6);
        mImages.add(R.drawable.icon7);
        mImages.add(R.drawable.icon8);
        mImages.add(R.drawable.icon9);
        mImages.add(R.drawable.icon10);
    }
}