package com.example.homeworkday06;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MainFragment();
            case 1:
                return new UserFragment();
            default:
                return new MainFragment();
        }
    }

    @Override
    public int getCount() {
        return 2; // We have 3 fragments
    }
}


