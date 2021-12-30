package com.example.myapplication.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.GalleryFragment;
import com.example.myapplication.GameFragment;
import com.example.myapplication.R;
import com.example.myapplication.phone.PhoneFragment;

public class BaseActivity extends FragmentActivity {
    protected ViewPager2 viewPager2;
    protected int layoutId = R.layout.activity_base;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        viewPager2 = findViewById(R.id.view_pager);
        viewPager2.setUserInputEnabled(false);
        viewPager2.setAdapter(createAdapter(this));
    }

    private FragmentStateAdapter createAdapter(FragmentActivity fragmentActivity){

        PhoneFragment phoneFragment = new PhoneFragment();
        GalleryFragment galleryFragment = new GalleryFragment();
        GameFragment gameFragment = new GameFragment();
        Fragment[] fragments = {phoneFragment, galleryFragment, gameFragment};

        return new FragmentStateAdapter(fragmentActivity) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments[position];
            }

            @Override
            public int getItemCount() {
                return fragments.length;
            }
        };
    }
}
