package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;


import com.example.myapplication.R;

public class ImageActivity extends Activity {

    Intent intent;
    String imageValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        intent = getIntent();
        imageValue = intent.getStringExtra("ImageValue");



        ImageView imageView = findViewById(R.id.gallery_image);
        Bitmap bmp = BitmapFactory.decodeResource(getApplicationContext().getResources(), Integer.parseInt(imageValue)); //to save memory
        bmp = Bitmap.createScaledBitmap(bmp, 300,300,false);
        imageView.setImageBitmap(bmp);
    }


}