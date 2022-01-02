package com.example.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.permission.PermissionRequest;

public class LoadingActivity extends Activity {

    PermissionRequest permissionRequest;
    int minimumLoadingSecond = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        permissionRequest = new PermissionRequest((Activity) this);
        permissionRequest.request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionRequest.onRequestPermissionResult(requestCode, permissions, grantResults);

        new Handler().postDelayed((Runnable) () -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }, minimumLoadingSecond * 1000);
    }


}
