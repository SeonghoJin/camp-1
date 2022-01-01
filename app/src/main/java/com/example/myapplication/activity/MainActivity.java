package com.example.myapplication.activity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.fragment.BlurFragment;

public class MainActivity extends BaseTabActivity {

    BlurFragment blurFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void inflateBlurView(Bitmap bitmap){
        blurFragment = new BlurFragment(mainLayout, bitmap);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_frame_layout, blurFragment).addToBackStack(null).commit();
    }

    public void removeBlurView(){
        getSupportFragmentManager().beginTransaction().remove(blurFragment).commit();
    }

}