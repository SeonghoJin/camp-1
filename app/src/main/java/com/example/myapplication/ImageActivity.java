package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ImageActivity extends Activity {

    Intent intent;
    String imageValue;
    int imageNum;
    ArrayList<String> imageIDs;

    Gallery gallery;
    ImageView image;
    TextView title;
    ImageButton infobutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        intent = getIntent();
        imageValue = intent.getStringExtra("ImageValue");
        imageNum = Integer.parseInt(intent.getStringExtra("ImageNum"));
        imageIDs = intent.getStringArrayListExtra("ImageIDs");



        gallery = (Gallery)findViewById(R.id.gallery1);
        image = (ImageView) findViewById(R.id.gallery_image);

        //title text
        title = (TextView) findViewById(R.id.title);
        Log.d(this.getClass().getName(), (String)title.getText());

        //infobutton
        infobutton = (ImageButton) findViewById(R.id.infobutton);

        //gallery adapter
        ImageAdapter galleryadapter = new ImageAdapter(getApplicationContext(), imageIDs, imageNum);
        gallery.setAdapter(galleryadapter);

        //show selected image and set gallery's selection to the selected image.

        Bitmap bmp = BitmapFactory.decodeFile(imageIDs.get(imageNum)); //to save memory
        bmp = Bitmap.createScaledBitmap(bmp, 400,400,false);

        image.setImageBitmap(bmp);
        gallery.setSelection(imageNum);
        title.setText("Picture"+imageNum);
        setInfobutton(infobutton, imageNum);
        // deal with click events
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bitmap bmp = BitmapFactory.decodeFile(imageIDs.get(i)); //to save memory
                bmp = Bitmap.createScaledBitmap(bmp, 400,400,false);
                image.setImageBitmap(bmp);

                title.setText("Picture"+i);
                setInfobutton(infobutton, i);


            }
        });

        }

    public void setInfobutton(ImageButton infobutton, int i){
        //button
        infobutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ImageActivity.this);
                builder.setMessage("Information")
                        .setMessage("Picture"+i)
                        .setCancelable(true)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }

        });

    }

}