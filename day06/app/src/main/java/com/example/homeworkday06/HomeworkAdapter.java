package com.example.homeworkday06;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeworkAdapter extends BaseQuickAdapter<ContentItem, BaseViewHolder> implements LoadMoreModule {

    public  HomeworkAdapter(List<ContentItem> list) {
        super(R.layout.fragment_item, list);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, @NotNull ContentItem item) {
//        helper.setImageResource(R.id.imContent, item.getDrawableId());
        ImageView imageView = helper.itemView.findViewById(R.id.imContent);
        TextView textView = helper.itemView.findViewById(R.id.tvContent);
        Button loveIv = helper.itemView.findViewById(R.id.btn_loveIv);
        Button loveTv = helper.itemView.findViewById(R.id.btn_loveTv);

        Glide.with(helper.itemView)
                .load(item.getImageUrl())
                .placeholder(R.drawable.placeholder) // 设置加载中的占位图
                .error(R.drawable.error) // 设置加载失败的占位图
                .into(imageView);

        helper.setText(R.id.tvContent, item.getText());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragment.loveImageBtn = loveIv;
                MainFragment.loveTextBtn = loveTv;
                MainFragment.contentItem = item;
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("ivContent", item.getImageUrl());
                intent.putExtra("tvContent", item.getText());
                intent.putExtra("loveImage", item.isLoveImage());
                intent.putExtra("loveText", item.isLoveText());
                v.getContext().startActivity(intent);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragment.loveImageBtn = loveIv;
                MainFragment.loveTextBtn = loveTv;
                MainFragment.contentItem = item;
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("ivContent", item.getImageUrl());
                intent.putExtra("tvContent", item.getText());
                intent.putExtra("loveImage", item.isLoveImage());
                intent.putExtra("loveText", item.isLoveText());
                v.getContext().startActivity(intent);
            }
        });

        loveIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setLoveImage(!item.isLoveImage());
                if (item.isLoveImage()) {
                    loveIv.setBackgroundResource(R.drawable.ic_love);
                } else {
                    loveIv.setBackgroundResource(R.drawable.ic_unlove);
                }
            }
        });

        loveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setLoveText(!item.isLoveText());
                if (item.isLoveText()) {
                    loveTv.setBackgroundResource(R.drawable.ic_love);
                } else {
                    loveTv.setBackgroundResource(R.drawable.ic_unlove);
                }
            }
        });
    }
}