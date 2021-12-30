package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> imageIDs;
    DisplayMetrics mMetrics;
    int layout;
    LayoutInflater inf;
    Integer selectedImageId;
    public ImageAdapter(Context context, ArrayList<String> imageIDs, Integer selectedImageId){
        this.context = context;
        this.imageIDs = imageIDs;
        this.selectedImageId = selectedImageId;
    }
    @Override
    public int getCount() {
        return imageIDs.size();
    }

    @Override
    public Object getItem(int i) {
        return imageIDs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);

        Bitmap bmp = BitmapFactory.decodeFile(imageIDs.get(i)); //to save memory
        bmp = Bitmap.createScaledBitmap(bmp, 400,400,false);

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageBitmap(bmp);

        imageView.setLayoutParams(new Gallery.LayoutParams(200,200 ));
        return imageView;

    }
}
