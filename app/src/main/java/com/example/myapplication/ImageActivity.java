package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

public class ImageActivity extends Activity {

    Intent intent;
    String imageValue;
    int imageNum;
    ArrayList<String> imageIDs;

    Gallery gallery;
    PhotoView image;
    TextView title;
    ImageButton infoButton;
    ImageButton deleteButton;
    ImageAdapter galleryadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        intent = getIntent();
        imageValue = intent.getStringExtra("ImageValue");
        imageNum = Integer.parseInt(intent.getStringExtra("ImageNum"));
        imageIDs = intent.getStringArrayListExtra("ImageIDs");


        gallery = (Gallery) findViewById(R.id.gallery1);
        image = (PhotoView) findViewById(R.id.gallery_image);

        //title text
        title = (TextView) findViewById(R.id.title);
        Log.d(this.getClass().getName(), (String) title.getText());

        //infobutton
        infoButton = (ImageButton) findViewById(R.id.infobutton);
        deleteButton = (ImageButton) findViewById(R.id.deletebutton);

        //gallery adapter
        galleryadapter = new ImageAdapter(getApplicationContext(), imageIDs, imageNum);
        gallery.setAdapter(galleryadapter);

        //show selected image and set gallery's selection to the selected image.

//        Bitmap bmp = BitmapFactory.decodeFile(imageIDs.get(imageNum)); //to save memory
//        bmp = Bitmap.createScaledBitmap(bmp, 400, 400, false);
//
//        image.setImageBitmap(bmp);
        createImageView(image, imageIDs, imageNum);


        gallery.setSelection(imageNum);
        title.setText("Picture" + imageNum);

        setInfobutton(infoButton, imageNum);
        setDeleteButton(deleteButton, imageNum, imageIDs);
        // deal with click events
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Bitmap bmp = BitmapFactory.decodeFile(imageIDs.get(i)); //to save memory
//                bmp = Bitmap.createScaledBitmap(bmp, 400, 400, false);
//                image.setImageBitmap(bmp);

                createImageView(image, imageIDs, i);

                title.setText("Picture" + i);
                setInfobutton(infoButton, i);
                setDeleteButton(deleteButton, i, imageIDs);


            }
        });

    }

    public void setInfobutton(ImageButton infoButton, int i) {
        //button
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ImageActivity.this);
                builder.setMessage("Information")
                        .setMessage("Picture" + i)
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

    public void setDeleteButton(ImageButton deleteButton, int i, ArrayList<String> imageIDs) {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ImageActivity.this);
                System.out.println("flag2");
                builder.setMessage("지우시겠습니까?")
                        .setCancelable(true)
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                galleryadapter = (ImageAdapter) AdapterUtils.removeFile(imageIDs, i, galleryadapter);

                                if( i == imageIDs.size() && i != 0){
                                    createImageView(image, imageIDs, i-1);
                                }
                                else if (imageIDs.size() == 0){
                                    image.setImageResource(R.drawable.no_image);
                                }
                                else{
                                    createImageView(image, imageIDs, i);
                                }
                            }
                        })
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }

    public void createImageView(PhotoView image, ArrayList<String>imgIDs, int imgNum ){
        Bitmap bmp = BitmapFactory.decodeFile(imgIDs.get(imgNum)); //to save memory
        bmp = Bitmap.createScaledBitmap(bmp, 400, 400, false);

        image.setImageBitmap(bmp);
    }

}
