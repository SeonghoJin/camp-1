package com.example.myapplication.permission;

import android.Manifest;
import android.app.Activity;

import androidx.core.app.ActivityCompat;

public class CallPermission extends Permission {

    public CallPermission(Activity activity) {
        super(activity);
    }

    @Override
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
    }

    @Override
    public void request() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, PermissionRequestNumber.CALL_PERMISSION.ordinal());
    }
}

