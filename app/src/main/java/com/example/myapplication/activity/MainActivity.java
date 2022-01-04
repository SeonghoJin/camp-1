package com.example.myapplication.activity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.fragment.BlurFragment;
import com.example.myapplication.map.MarkerVO;
import com.google.android.gms.maps.model.Marker;

public class MainActivity extends BaseTabActivity {

    BlurFragment blurFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void inflateBlurView(MarkerVO marker, Bitmap bitmap){
        blurFragment = new BlurFragment(mainLayout, bitmap, marker);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_frame_layout, blurFragment).addToBackStack(null).commit();
    }

    public void removeBlurView(){
        getSupportFragmentManager().beginTransaction().remove(blurFragment).commit();
    }

    @Override
    public void onBackPressed() {
    }
}