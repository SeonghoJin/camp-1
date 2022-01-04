package com.example.myapplication.gallery;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakePicture {

    static String imageFilePath;
    public static String dispatchTakePictureIntent(Activity activity) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile(activity);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(activity,
                        "com.example.android.fileprovider",
                        photoFile);

                System.out.println("photoURI == " + photoURI);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                activity.startActivityForResult(takePictureIntent, 1);
            }
        }
        return imageFilePath;
    }

    public static void onCaptureCallback(int requestCode, int resultCode, Intent intent, int realRequestCode, Runnable runnable) {
        if(requestCode != realRequestCode){
            return;
        }

        if(resultCode != -1){
            return;
        }

        runnable.run();
    }

    private static File createImageFile(Activity activity) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(String.valueOf(activity.getExternalCacheDir()));

        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir

        );
        imageFilePath = image.getAbsolutePath();
        if(image.exists()){
            image.delete();
        }
        return image;
    }
}
