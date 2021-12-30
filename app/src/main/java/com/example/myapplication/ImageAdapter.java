package com.example.myapplication;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    Context context;
    Integer[] imageIDs;
    DisplayMetrics mMetrics;
    int layout;
    LayoutInflater inf;
    Integer selectedImageId;
    public ImageAdapter(Context context, Integer[] imageIDs, Integer selectedImageId){
        this.context = context;
        this.imageIDs = imageIDs;
        this.selectedImageId = selectedImageId;
    }
    @Override
    public int getCount() {
        return imageIDs.length;
    }

    @Override
    public Object getItem(int i) {
        return imageIDs[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(imageIDs[i]);

        imageView.setLayoutParams(new Gallery.LayoutParams(200,200 ));
        return imageView;

    }
}
