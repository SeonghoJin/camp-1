package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;


import com.example.myapplication.R;

import java.util.Arrays;

public class ImageActivity extends Activity {

    Intent intent;
    String imageValue;
    String[] images;
    int imageNum;
    Integer[] imageIDs;

    Gallery gallery;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        intent = getIntent();
        imageValue = intent.getStringExtra("ImageValue");
        imageNum = Integer.parseInt(intent.getStringExtra("ImageNum"));
        images = intent.getStringArrayExtra("ImageIDs");

        imageIDs = new Integer[images.length];
        for (int j = 0; j < images.length; j++) {
            imageIDs[j] = Integer.parseInt(images[j]);
        }


        gallery = (Gallery)findViewById(R.id.gallery1);
        image = (ImageView) findViewById(R.id.gallery_image);

        ImageAdapter galleryadapter = new ImageAdapter(getApplicationContext(), imageIDs, imageNum);
        gallery.setAdapter(galleryadapter);
        System.out.println("flag1");

        image.setImageResource(imageIDs[imageNum]);
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(imageNum);
                image.setImageResource(imageIDs[i]);
            }
        });

        gallery.setSelection(imageNum);



//
//
//        ImageView imageView = findViewById(R.id.gallery_image);
//        Bitmap bmp = BitmapFactory.decodeResource(getApplicationContext().getResources(), imageIDs[imageNum]); //to save memory
//        bmp = Bitmap.createScaledBitmap(bmp, 300,300,false);
//        imageView.setImageBitmap(bmp);
    }


}