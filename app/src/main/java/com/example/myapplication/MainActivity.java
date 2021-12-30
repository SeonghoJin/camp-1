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

public class MainActivity extends BaseTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionRequest permissionRequest = new PermissionRequest((Activity)this);
        permissionRequest.request();
    }

}