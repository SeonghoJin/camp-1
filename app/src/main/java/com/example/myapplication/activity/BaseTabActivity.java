package com.example.myapplication.activity;

import android.os.Bundle;

import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class BaseTabActivity extends BaseAnimationActivity {
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.layoutId = R.layout.activity_tab_base;
        super.onCreate(savedInstanceState);
        tabLayout = findViewById(R.id.tabs);
        setTabLayoutMediator(tabLayout, viewPager2);
    }

    protected void setTabLayoutMediator(TabLayout tabLayout, ViewPager2 viewPager2) {

        String[] fragmentTitles = new String[]{"연락처", "갤러리", "게임"};

        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> {
            tab.setText(fragmentTitles[position]);
        })).attach();
    }
}
