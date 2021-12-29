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

public class GalleryImageAdapter extends BaseAdapter {
    private DisplayMetrics mMetrics;
    private Context context;
    private Integer[] imageIDs;

    public GalleryImageAdapter(Context context, Integer[] imageIDs, DisplayMetrics mMetrics){
        this.context = context;
        this.imageIDs = imageIDs;
        this.mMetrics = mMetrics;
    }
    @Override
    public int getCount() {
//            return (null ! = imageIDs) ? imageIDs.length : 0;
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
    //creating new imageView
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ImageView imageView;

        int rowWidth = (mMetrics.widthPixels - mMetrics.widthPixels/15) / 3;

        if(null != convertView){
            imageView = (ImageView)convertView;
        }else{

            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), imageIDs[i]); //to save memory
            bmp = Bitmap.createScaledBitmap(bmp, 50,50,false);
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(rowWidth, rowWidth));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(1,1,1,1);
            imageView.setImageBitmap(bmp);

//                ImageClickListener imageViewClickListener = new ImageClickListener(context, imageIDs[i]);
//                imageView.setOnClickListener(imageViewClickListener);

        }
        imageView.setImageResource(imageIDs[i]);

        return imageView;




    }
}
