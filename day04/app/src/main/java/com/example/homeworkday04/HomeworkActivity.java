package com.example.homeworkday04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeworkActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<View> mViews;  //存放视图的数组
    private View view1,view2,view3;
    private PagerAdapter mPagerAdapter;//适配器

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;

    String[] status = {"黑", "神", "话", "悟", "空", "猿", "神", "启", "动", "！"};
    int[] icons = {R.drawable.icon1,
            R.drawable.icon2,
            R.drawable.icon3,
            R.drawable.icon4,
            R.drawable.icon5,
            R.drawable.icon6,
            R.drawable.icon7,
            R.drawable.icon8,
            R.drawable.icon9,
            R.drawable.icon10};
    private int mod = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);

        mViewPager=findViewById(R.id.viewpager);// 实例化viewpager控件

        LayoutInflater inflater = getLayoutInflater();//获取布局对象管理
        view1=inflater.inflate(R.layout.fragment_homework,null);//实例化view
        view2=inflater.inflate(R.layout.fragment_recycler_view,null);
//        view3=inflater.inflate(R.layout.layout3,null);

        setHomeworkButton(view1);
        setRecyclerViewButton(view2);

        mViews=new ArrayList<View>();//将要显示的布局存放到list数组
        mViews.add(view1);
        mViews.add(view2);
//        mViews.add(view3);

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

    private void setHomeworkButton(View view) {
        Button confirmBtn = view.findViewById(R.id.btn_confirm);
        Button clearBtn = view.findViewById(R.id.btn_clear);
        Button exitBtn = view.findViewById(R.id.btn_exit);
        EditText editText = view.findViewById(R.id.edit_name);
        RadioButton male = view.findViewById(R.id.radio_male);
        RadioButton female = view.findViewById(R.id.radio_female);
        CheckBox checkBox1 = view.findViewById(R.id.checkBox1);
        CheckBox checkBox2 = view.findViewById(R.id.checkBox2);
        CheckBox checkBox3 = view.findViewById(R.id.checkBox3);
        Switch aSwitch = view.findViewById(R.id.switch_marry);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder str = new StringBuilder();
                str.append("Name:").append(editText.getText().toString()).append('\n');
                if (male.isChecked()) { str.append("性别：男\n"); }
                if (female.isChecked()) { str.append("性别：女\n"); }
                if (checkBox1.isChecked() | checkBox2.isChecked() | checkBox3.isChecked()){
                    str.append("爱好：");
                }
                if (checkBox1.isChecked()) { str.append("美食"); }
                if (checkBox2.isChecked()) { str.append("阅读"); }
                if (checkBox3.isChecked()) { str.append("编程"); }
                if (checkBox1.isChecked() | checkBox2.isChecked() | checkBox3.isChecked()){
                    str.append("\n");
                }
                if (aSwitch.isChecked()) { str.append("婚姻：已婚\n"); }
                else { str.append("婚姻：未婚\n"); }
                str.append(LocalTime.now().toString());
                Toast.makeText(HomeworkActivity.this, str, Toast.LENGTH_LONG).show();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                male.setChecked(false);
                female.setChecked(false);
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                aSwitch.setChecked(false);
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private ArrayList<GameBean> data = new ArrayList<>();

    private void setRecyclerViewButton(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
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

        Button addItem = view.findViewById(R.id.item_add);
        Button removeItem = view.findViewById(R.id.item_remove);
        EditText editText = view.findViewById(R.id.item_edit);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() == 0) return;
                int position = Integer.parseInt(editText.getText().toString());
                if (position > data.size()){
                    position = data.size();
                    String str = new String("position过大，已调整为：" + data.size());
                    Toast.makeText(HomeworkActivity.this, str, Toast.LENGTH_SHORT).show();
                }
                if (position < 0){
                    position = 0;
                    String str = new String("position过小，已调整为：" + 0);
                    Toast.makeText(HomeworkActivity.this, str, Toast.LENGTH_SHORT).show();
                }
                data.add(position, new GameBean(icons[new Random().nextInt(mod)],
                        "Undefined", "?"));
                adapter.notifyItemInserted(position);
                adapter.notifyItemRangeChanged(position, data.size());
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setGameName("position" + i);
                }
//                adapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(position);
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() == 0) return;
                int position = Integer.parseInt(editText.getText().toString());
                if (position > data.size()){
                    position = data.size();
                    String str = new String("position过大，已调整为：" + data.size());
                    Toast.makeText(HomeworkActivity.this, str, Toast.LENGTH_SHORT).show();
                }
                if (position < 0){
                    position = 0;
                    String str = new String("position过小，已调整为：" + 0);
                    Toast.makeText(HomeworkActivity.this, str, Toast.LENGTH_SHORT).show();
                }
                data.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, data.size());
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setGameName("position" + i);
                }
//                adapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(position);
            }
        });
    }

    private void setData() {
        for (int i = 0; i < 20; i ++) {
            GameBean game = new GameBean();
            game.setGameName("position" + i);
            game.setGameIcon(icons[i % mod]);
            game.setGameStatus(status[i % mod]);
            data.add(game);
        }
    }
}