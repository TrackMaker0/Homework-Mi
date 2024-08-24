package com.example.homeworkday06;

import android.graphics.drawable.Drawable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DemoAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> implements LoadMoreModule {

    public DemoAdapter(List<Integer> list) {
        super(R.layout.layout_demo, list);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, @NotNull Integer item) {
        helper.setImageResource(R.id.imageView2, item);
    }
}