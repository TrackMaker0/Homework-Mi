package com.example.homeworkday03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class ExampleActivity extends AppCompatActivity implements DemoFragment.TopPageActionListener{

    @Override
    public void onTopPageAction() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, DemoFragment2.class, null)
                .addToBackStack(null)
                .commitNow();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        Bundle bundle = new Bundle();
        bundle.putInt("key", 5);

        // 静态添加： 在xml中设置android:name为要使用的Fragment
        // 动态添加： 下面的代码
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.fragment_container_view, DemoFragment.class, bundle)
//                .commit();

        // 将事务添加到回退栈（可以回退操作）
//      transaction.addToBackStack(null); // 也可以传递一个非空的字符串作为事务的名称


        DemoFragment demoFragment = new DemoFragment();
        DemoFragment2 demoFragment2 = new DemoFragment2();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container_view, demoFragment, "demoFragmentTag");
        transaction.commit();

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction addTransaction = getSupportFragmentManager().beginTransaction();
                addTransaction.add(R.id.fragment_container_view, demoFragment2, "demoFragment2Tag");
                addTransaction.addToBackStack(null);
                addTransaction.commit();
            }
        });

        findViewById(R.id.btn_hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction hideTransaction = getSupportFragmentManager().beginTransaction();
                hideTransaction.hide(demoFragment); // 注意这里传递的是 Fragment 实例
                hideTransaction.commit();
            }
        });

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction showTransaction = getSupportFragmentManager().beginTransaction();
                showTransaction.show(demoFragment); // 注意这里也是传递 Fragment 实例
                showTransaction.commit();
            }
        });

        findViewById(R.id.btn_replace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, demoFragment2)
                        .setCustomAnimations(
                                R.anim.slide_in,
                                R.anim.slide_out,
                                R.anim.fade_in,
                                R.anim.fade_out)
                        .addToBackStack(null)
                        .commit();
            }
        });

        findViewById(R.id.btn_add2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container_view2, new DemoFragment2())
                        .addToBackStack(null)
                        .commit();
            }
        });

    }
}