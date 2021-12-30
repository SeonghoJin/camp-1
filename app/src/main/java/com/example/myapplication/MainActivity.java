package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.os.Bundle;

import com.example.myapplication.permission.PermissionRequest;
import com.example.myapplication.phone.PhoneFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends BaseActivity {

    PhoneFragment phoneFragment;
    GalleryFragment galleryFragment;
    GameFragment gameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionRequest permissionRequest = new PermissionRequest((Activity)this);
        permissionRequest.request();
//        setContentView(R.layout.activity_main);

        phoneFragment = new PhoneFragment();
        galleryFragment = new GalleryFragment();
        gameFragment = new GameFragment();

        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                System.out.println(position);
                if(position == 0){
                    return phoneFragment;
                }

                if(position == 1){
                    return galleryFragment;
                }

                if(position == 2){
                    return gameFragment;
                }

                return null;
            }

            @Override
            public int getItemCount() {
                return 3;
            }
        });

//        getSupportFragmentManager().beginTransaction().add(R.id.container, phoneFragment).commit();


//        tabs = findViewById(R.id.tabs);
//        tabs.addTab(tabs.newTab().setText("Phone"));
//        tabs.addTab(tabs.newTab().setText("Gallery"));
//        tabs.addTab(tabs.newTab().setText("Game"));


//        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                int position = tab.getPosition();
//                Fragment selected = null;
//                if(position == 0)
//                    selected = phoneFragment;
//                else if(position == 1)
//                    selected = galleryFragment;
//                else if(position == 2)
//                    selected = gameFragment;
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

    }

}