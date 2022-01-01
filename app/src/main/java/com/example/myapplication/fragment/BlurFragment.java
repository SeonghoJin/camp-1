package com.example.myapplication.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
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
import com.example.myapplication.activity.MainActivity;
import com.skydoves.expandablelayout.ExpandableLayout;

import jp.wasabeef.blurry.Blurry;

public class BlurFragment extends Fragment {

    private ImageView blurView;
    private ViewGroup root;
    private Bitmap bitmap;
    private Button button;
    private ExpandableLayout expandableLayout1;
    private ExpandableLayout expandableLayout2;


    public BlurFragment(ViewGroup view, Bitmap bitmap) {
        this.root = view;
        this.bitmap = bitmap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.blur_view_fragment, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(ViewGroup rootView){
        blurView = rootView.findViewById(R.id.blur_view);
        expandableLayout1 = rootView.findViewById(R.id.expanded_menu);
        expandableLayout2 = rootView.findViewById(R.id.expanded_menu1);
        button = rootView.findViewById(R.id.pass_button);
        expandableLayout1.parentLayout.setOnClickListener(view -> expandableLayout1.toggleLayout());
        expandableLayout2.parentLayout.setOnClickListener(view -> expandableLayout2.toggleLayout());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).removeBlurView();
            }
        });
        initBlur();
    }

    private void initBlur(){
        blurView.setImageBitmap(bitmap);
        Blurry.with(getContext())
                .sampling(4)
                .from(bitmap)
                .into(blurView);
    }
}
