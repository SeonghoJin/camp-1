package com.example.myapplication.activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class BaseTabActivity extends BaseActivity {
    private TabLayout tabLayout;
    protected ViewGroup mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.layoutId = R.layout.activity_tab_base;
        super.onCreate(savedInstanceState);
        tabLayout = findViewById(R.id.tabs);
        setTabLayoutMediator(tabLayout, viewPager2);
        mainLayout = findViewById(R.id.main_layout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ColorStateList colorStateList = getResources().getColorStateList(R.color.iphone_links);
                tab.view.findViewById(R.id.tab_image).setBackgroundTintList(colorStateList);
                tab.view.findViewById(R.id.tab_text).setBackgroundTintList(colorStateList);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ColorStateList colorStateList = getResources().getColorStateList(R.color.iphone_settings);
                tab.view.findViewById(R.id.tab_image).setBackgroundTintList(colorStateList);
                tab.view.findViewById(R.id.tab_text).setBackgroundTintList(colorStateList);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                ColorStateList colorStateList = getResources().getColorStateList(R.color.iphone_links);
                tab.view.findViewById(R.id.tab_image).setBackgroundTintList(colorStateList);
                tab.view.findViewById(R.id.tab_text).setBackgroundTintList(colorStateList);
            }
        });

        tabLayout.selectTab(tabLayout.getTabAt(0));

    }

    protected void setTabLayoutMediator(TabLayout tabLayout, ViewPager2 viewPager2) {
        String[] fragmentTitles = new String[]{"연락처", "갤러리", "지도"};
        int[] layoutIds = new int[]{R.layout.phone_tab, R.layout.gallery_tab, R.layout.map_tab};

        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> {
            tab.setCustomView(layoutIds[position]);
            tab.setText(fragmentTitles[position]);
        })).attach();
    }
}
