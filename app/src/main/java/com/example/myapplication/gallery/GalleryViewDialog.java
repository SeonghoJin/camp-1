package com.example.myapplication.gallery;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.myapplication.FolderImagesAdapter;
import com.example.myapplication.ImageAdapter;
import com.example.myapplication.R;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GalleryViewDialog extends Dialog {
    private Context context;
    private GalleryDao galleryDao;
    private GalleryFolder galleryFolder;
    private ImageAdapter imageAdapter;
    private PhotoView image;
    private int i;
    private Activity activity;

    public GalleryViewDialog(Context context,
                       GalleryDao galleryDao,
                       GalleryFolder galleryFolder,
                       ImageAdapter imageAdapter,
                       PhotoView image, int i,
                             Activity activity){
        super(context);
        this.context = context;
        this.galleryDao = galleryDao;
        this.galleryFolder = galleryFolder;
        this.imageAdapter = imageAdapter;
        this.image = image;
        this.i = i;
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagedialog);

        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button delete = findViewById(R.id.deleteimage);
        Button cancel = findViewById(R.id.cancel);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(galleryFolder.images.size() == 1){
                    imageAdapter.delete(0);
                    galleryDao.updateFolders(galleryFolder);
                    dismiss();
                    activity.finish();
                    return;
                }

                int temp = i;

                if(temp >= galleryFolder.images.size() - 1){
                    while(temp > galleryFolder.images.size() - 1){
                        temp--;
                    }
                    imageAdapter.delete(temp);
                    galleryDao.updateFolders(galleryFolder);
                    createImage(image, galleryFolder.images, temp-1);dismiss();
                    dismiss();
                    return;
                }

                imageAdapter.delete(i);
                galleryDao.updateFolders(galleryFolder);
                createImage(image, galleryFolder.images, i);
                dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();

            }
        });
    }
    public void createImage(PhotoView image, ArrayList<String> imgIDs, int imgNum ){
        Bitmap bmp = BitmapFactory.decodeFile(imgIDs.get(imgNum)); //to save memory
        bmp = Bitmap.createScaledBitmap(bmp, 600,bmp.getHeight()/(bmp.getWidth()/600), false);

        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imgIDs.get(imgNum));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int exifOrientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        int exifDegree = exifOrientationToDegrees(exifOrientation);
        bmp = rotate(bmp, exifDegree);

        image.setImageBitmap(bmp);
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
