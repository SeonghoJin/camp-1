package com.example.myapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.map.MarkerVO;
import com.google.android.gms.maps.model.Marker;
import com.skydoves.expandablelayout.ExpandableLayout;

import java.util.Arrays;

import jp.wasabeef.blurry.Blurry;

public class BlurFragment extends Fragment {

    private ImageView blurView;
    private ViewGroup root;
    private Bitmap bitmap;
    private Button button;
    private ExpandableLayout expandableLayout1;
    private ExpandableLayout expandableLayout2;
    private MarkerVO markerVO;
    private ImageView imageView;


    public BlurFragment(ViewGroup view, Bitmap bitmap, MarkerVO marker) {
        this.root = view;
        this.bitmap = bitmap;
        this.markerVO = marker;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.blur_view_fragment, container, false);
        initView(rootView);
        bindMarker(markerVO);
        initBlur();
        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initView(ViewGroup rootView){
        blurView = rootView.findViewById(R.id.blur_view);
        expandableLayout1 = rootView.findViewById(R.id.expanded_menu);
        expandableLayout2 = rootView.findViewById(R.id.expanded_menu1);
        button = rootView.findViewById(R.id.pass_button);
        imageView = rootView.findViewById(R.id.image_view);

        expandableLayout1.parentLayout.setOnClickListener(view -> expandableLayout1.toggleLayout());
        expandableLayout2.parentLayout.setOnClickListener(view -> expandableLayout2.toggleLayout());
        button.setOnClickListener(view -> ((MainActivity)getActivity()).removeBlurView());

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void bindMarker(MarkerVO marker) {
        ((TextView)expandableLayout1.parentLayout.findViewById(R.id.parent_text_view))
                .setText(marker.title + " 내부 모습");

        LinearLayout linearLayout = (LinearLayout) expandableLayout1.secondLayout;
        clearLayout(linearLayout);
        addViewsInteriorImages(linearLayout, marker.interiorBuildingImageResourceIds);

        ((TextView)expandableLayout2.parentLayout.findViewById(R.id.parent_text_view))
                .setText(marker.title + "에 대해서");

        ((TextView)expandableLayout2.secondLayout.findViewById(R.id.child_text_view))
                .setText(marker.description);

        imageView.setImageDrawable(getResources().getDrawable(marker.imageResourceId));
    }

    private void clearLayout(ViewGroup layout){
        layout.removeAllViewsInLayout();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addViewsInteriorImages(ViewGroup layout, int[] imagesIds) {
        Activity activity = getActivity();

        Arrays.stream(imagesIds).forEach((imagesResourceId) -> {
            int layoutWidth = (int)(getWindowWidth() * 0.85);
            System.out.println(layoutWidth);
            CardView cardView = new CardView(activity);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(layoutWidth,layoutWidth);
            layoutParams.setMargins(30, 30, 30, 30);
            cardView.setLayoutParams(layoutParams);
            cardView.setRadius(20);

            ImageView imageView = new ImageView(activity);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            imageView.setImageResource(imagesResourceId);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);


            cardView.addView(imageView);
            layout.addView(cardView);
            System.out.println(layout.getWidth());
        });
    }

    private void initBlur(){
        blurView.setImageBitmap(bitmap);
        Blurry.with(getContext())
                .sampling(4)
                .from(bitmap)
                .into(blurView);
    }

    private int getWindowWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
