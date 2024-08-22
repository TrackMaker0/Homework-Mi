package com.example.homeworkday04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    ArrayList<GameBean> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        // 1.准备数据
        setData();
        // 2.获取适配器对象
        GameRecyclerAdapter adapter = new GameRecyclerAdapter(data);
        // 3.设置适配器
        recyclerView.setAdapter(adapter);
        // 4.设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 5.设置分割线
        DividerItemDecoration decoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.divide_line));
        recyclerView.addItemDecoration(decoration);

        Button addItem = findViewById(R.id.item_add);
        Button removeItem = findViewById(R.id.item_remove);
        EditText editText = findViewById(R.id.item_edit);

        GameBean addGameBean =new GameBean(R.drawable.icon1,
                "AddItem", "Added");

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() == 0) return;
                int position = Integer.parseInt(editText.getText().toString());
                data.add(position, addGameBean);
                adapter.notifyItemInserted(position);
                adapter.notifyItemRangeChanged(position, data.size());
                recyclerView.scrollToPosition(position);
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() == 0) return;
                int position = Integer.parseInt(editText.getText().toString());
                data.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, data.size());
                recyclerView.scrollToPosition(position);
            }
        });
    }

    private void setData() {
        String[] status = {"预约", "下载", "安装", "开始游戏"};
        int[] icons = {R.drawable.icon1,
                R.drawable.icon2,
                R.drawable.icon3,
                R.drawable.icon4};

        for (int i = 0; i < 50; i ++) {
            GameBean game = new GameBean();
            game.setGameName("游戏数据" + i);
            game.setGameIcon(icons[i % 4]);
            game.setGameStatus(status[i % 4]);
            data.add(game);
        }
    }
}