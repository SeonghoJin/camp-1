package com.example.myapplication;

import static com.example.myapplication.gallery.RotatePicture.exifOrientationToDegrees;
import static com.example.myapplication.gallery.RotatePicture.rotate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.io.IOException;
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

    public void delete(int i ){
        imageIDs.remove(i);
        notifyDataSetChanged();
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);

        Bitmap bmp = BitmapFactory.decodeFile(imageIDs.get(i)); //to save memory
        bmp = Bitmap.createScaledBitmap(bmp, 600,bmp.getHeight()/(bmp.getWidth()/600),false);

        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imageIDs.get(i));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int exifOrientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        int exifDegree = exifOrientationToDegrees(exifOrientation);
        bmp = rotate(bmp, exifDegree);

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(bmp);

        imageView.setLayoutParams(new Gallery.LayoutParams(200,200 ));
        return imageView;

    }


}
