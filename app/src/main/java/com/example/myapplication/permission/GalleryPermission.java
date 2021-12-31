package com.example.myapplication.permission;

import android.Manifest;
import android.app.Activity;

import androidx.core.app.ActivityCompat;

public class GalleryPermission extends Permission{

    public GalleryPermission(Activity activity) {
        super(activity);
    }

    @Override
    void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {

    }

    @Override
    void request() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.ACCESS_FINE_LOCATION
                },
                PermissionRequestNumber.GALLERY_PERMISSION.ordinal());

    }
}
