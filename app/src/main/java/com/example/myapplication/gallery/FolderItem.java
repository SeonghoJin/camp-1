package com.example.myapplication.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.io.IOException;
import java.util.ArrayList;

public class FolderItem extends LinearLayout {
    TextView folderName;
    ImageView thumbnail;

    public FolderItem(Context context) {
        super(context);

        init(context);
    }

    public FolderItem(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);

        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.folderitem, this, true);

        folderName = (TextView) findViewById(R.id.folderName);
        thumbnail = (ImageView) findViewById(R.id.thumbnail);

    }
    public void setItem(String folderName, ArrayList<String> imageIDs, int rowWidth){
        this.folderName.setText(folderName);
        if (imageIDs == null || imageIDs.size() == 0){
            thumbnail.setImageResource(R.drawable.no_image);
            return;
        }

        Bitmap bmp = BitmapFactory.decodeFile(imageIDs.get(0));
        bmp = Bitmap.createScaledBitmap(bmp, 600,bmp.getHeight()/(bmp.getWidth()/600),false);

        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imageIDs.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int exifOrientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        int exifDegree = exifOrientationToDegrees(exifOrientation);
        bmp = rotate(bmp, exifDegree);

//            imageView = new ImageView(context);
        thumbnail.setLayoutParams(new LinearLayout.LayoutParams(rowWidth, rowWidth));
        thumbnail.setScaleType(ImageView.ScaleType.FIT_XY);
        thumbnail.setPadding(1,1,1,1);
        thumbnail.setImageBitmap(bmp);

//        }


    return;
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
