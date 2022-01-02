package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.gallery.GalleryFolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FolderImagesAdapter extends BaseAdapter {
    private DisplayMetrics mMetrics;
    private Context context;
    private ArrayList<String> imageIDs;

    public FolderImagesAdapter(Context context, ArrayList<String> imageIDs, DisplayMetrics mMetrics){
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

    public void insert(String image) {
        this.imageIDs.add(image);
        this.notifyDataSetChanged();
    }

    public void delete(int i){
        this.imageIDs.remove(i);
        this.notifyDataSetChanged();
    }

    @Override
    //creating new imageView
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ImageView imageView = (ImageView)convertView;

        int rowWidth = (mMetrics.widthPixels - mMetrics.widthPixels/15) / 3;

//        if(null != convertView){
//            imageView = (ImageView)convertView;
//        }else{
        if (imageIDs == null) return convertView;

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

        imageView = new ImageView(context);
        imageView.setLayoutParams(new GridView.LayoutParams(rowWidth, rowWidth));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setPadding(1,1,1,1);
        imageView.setImageBitmap(bmp);

//        }

        return imageView;
    }

    public int exifOrientationToDegrees(int exifOrientation){

        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        }else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;

    }

    public Bitmap rotate(Bitmap bitmap, int degrees)
    {
        if(degrees != 0 && bitmap != null)
        {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) bitmap.getWidth() / 2,
                    (float) bitmap.getHeight() / 2);

            try
            {
                Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0,
                        bitmap.getWidth(), bitmap.getHeight(), m, true);
                if(bitmap != converted)
                {
                    bitmap.recycle();
                    bitmap = converted;
                }
            }
            catch(OutOfMemoryError ex)
            {
            }
        }
        return bitmap;
    }


}
