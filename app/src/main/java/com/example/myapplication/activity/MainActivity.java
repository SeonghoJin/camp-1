package com.example.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.activity.BaseTabActivity;
import com.example.myapplication.permission.PermissionRequest;

public class MainActivity extends BaseTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PermissionRequest permissionRequest = new PermissionRequest((Activity)this);
        permissionRequest.request();


    }

}