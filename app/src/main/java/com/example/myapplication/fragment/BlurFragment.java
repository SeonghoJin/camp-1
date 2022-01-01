package com.example.myapplication.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R;
import com.example.myapplication.SupportRenderScriptBlur;

import eightbitlab.com.blurview.BlurView;
import jp.wasabeef.blurry.Blurry;

public class BlurFragment extends Fragment {

    private ImageView blurView;
    private ViewGroup root;
    private Bitmap bitmap;
    private Button button;


    public BlurFragment(ViewGroup view, Bitmap bitmap) {
        this.root = view;
        this.bitmap = bitmap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.blue_view_fragment, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(ViewGroup rootView){
        blurView = rootView.findViewById(R.id.blur_view);
        initBlur();
    }

    private void initBlur(){
        blurView.setImageBitmap(bitmap);
        Blurry.with(getContext())
                .sampling(10)
                .from(bitmap)
                .into(blurView);
    }
}
