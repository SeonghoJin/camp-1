package com.example.myapplication;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

public class BaseActivity extends FragmentActivity {
    protected ViewPager2 viewPager2;
    protected int layoutId = R.layout.activity_base;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        viewPager2 = findViewById(R.id.view_pager);
    }
}
