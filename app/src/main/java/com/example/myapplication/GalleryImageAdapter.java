package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GalleryImageAdapter extends BaseAdapter {
    private DisplayMetrics mMetrics;
    private Context context;
    private ArrayList<String> imageIDs;

    public GalleryImageAdapter(Context context, ArrayList<String> imageIDs, DisplayMetrics mMetrics){
        this.context = context;
        this.imageIDs = imageIDs;
        this.mMetrics = mMetrics;
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
    //creating new imageView
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ImageView imageView;

        int rowWidth = (mMetrics.widthPixels - mMetrics.widthPixels/15) / 3;

        if(null != convertView){
            imageView = (ImageView)convertView;
        }else{
            Bitmap bmp = BitmapFactory.decodeFile(imageIDs.get(i)); //to save memory
            bmp = Bitmap.createScaledBitmap(bmp, 400,400,false);
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(rowWidth, rowWidth));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(1,1,1,1);
            imageView.setImageBitmap(bmp);

        }

        return imageView;
    }

}
