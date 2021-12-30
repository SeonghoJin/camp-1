package com.example.myapplication;

import android.os.Bundle;
import androidx.viewpager2.widget.ViewPager2;

public class BaseAnimationActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewPager2.setPageTransformer(createPageTransformer());
    }

    public ViewPager2.PageTransformer createPageTransformer(){
        return ((page, position) -> {
            float absPos = Math.abs(position);
            page.setTranslationY(absPos * 500f);
        });
    }
}
