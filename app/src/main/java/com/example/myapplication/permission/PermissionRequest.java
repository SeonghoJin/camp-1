package com.example.myapplication.permission;

import android.app.Activity;

public class PermissionRequest extends Permission{
    public PermissionRequest(Activity activity) {
        super(activity);
    }

    @Override
    void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {

    }

    @Override
    public void request() {
        CallPermission callPermission = new CallPermission(getActivity());
        callPermission.request();
    }
}
