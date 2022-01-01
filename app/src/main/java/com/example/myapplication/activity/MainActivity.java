package com.example.myapplication.activity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.fragment.BlurFragment;

public class MainActivity extends BaseTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void inflateBlurView(Bitmap bitmap){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_frame_layout, new BlurFragment(mainLayout, bitmap)).addToBackStack(null).commit();

    }

}