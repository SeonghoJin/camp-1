package com.example.myapplication;

import static com.example.myapplication.gallery.RotatePicture.exifOrientationToDegrees;
import static com.example.myapplication.gallery.RotatePicture.rotate;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.gallery.ConcreteGalleryDatabase;
import com.example.myapplication.gallery.GalleryDao;
import com.example.myapplication.gallery.GalleryFolder;
import com.example.myapplication.gallery.GalleryViewDialog;
import com.example.myapplication.gallery.ImageInfoDialog;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends Activity {

    Intent intent;
    String imageValue;
    int imageNum;

    Gallery gallery;
    PhotoView image;
    TextView title;
    ImageButton infoButton;
    ImageButton deleteButton;
    ImageAdapter galleryadapter;

    //for DB
    int key;
    GalleryFolder galleryFolder;
    GalleryDao galleryDao;
    List<GalleryFolder> galleryFolders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        intent = getIntent();
        key = intent.getIntExtra("key", -1);
        imageNum = intent.getIntExtra("imageNum", -1);

        galleryDao = ConcreteGalleryDatabase.getDatabase(getApplicationContext());
        galleryFolders = galleryDao.loadAllFolders();

        galleryFolder = galleryDao.getGalleryFolderbyKey(key);

        if (galleryFolder.images == null) {galleryFolder.images = new ArrayList<String>(0);}


        gallery = (Gallery) findViewById(R.id.gallery1);
        image = (PhotoView) findViewById(R.id.gallery_image);

        //title text
        title = (TextView) findViewById(R.id.title);
        Log.d(this.getClass().getName(), (String) title.getText());

        //infobutton
        infoButton = (ImageButton) findViewById(R.id.infobutton);
        deleteButton = (ImageButton) findViewById(R.id.deletebutton);

        //gallery adapter
        galleryadapter = new ImageAdapter(getApplicationContext(), galleryFolder.images, imageNum);
        gallery.setAdapter(galleryadapter);

        //show selected image and set gallery's selection to the selected image.

//        Bitmap bmp = BitmapFactory.decodeFile(imageIDs.get(imageNum)); //to save memory
//        bmp = Bitmap.createScaledBitmap(bmp, 400, 400, false);
//
//        image.setImageBitmap(bmp);
        createImageView(image, galleryFolder.images, imageNum);


        gallery.setSelection(imageNum);
        title.setText("Picture" + imageNum );

        setInfobutton(infoButton, imageNum);
        setDeleteButton(deleteButton, imageNum, galleryFolder.images);
        // deal with click events
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Bitmap bmp = BitmapFactory.decodeFile(imageIDs.get(i)); //to save memory
//                bmp = Bitmap.createScaledBitmap(bmp, 400, 400, false);
//                image.setImageBitmap(bmp);

                createImageView(image, galleryFolder.images, i);

                title.setText("Picture" + i);
                setInfobutton(infoButton, i);
                setDeleteButton(deleteButton, i, galleryFolder.images);


            }
        });

    }


    public void setInfobutton(ImageButton infoButton, int i) {
        //button
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageInfoDialog infoDialog = new ImageInfoDialog(ImageActivity.this, galleryFolder.folderName);
                infoDialog.show();
//                AlertDialog.Builder builder = new AlertDialog.Builder(ImageActivity.this);
//                builder.setMessage("Information")
//                        .setMessage("Picture" + i+ "\n" + "folder: " + galleryFolder.folderName)
//                        .setCancelable(true)
//                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                            }
//                        });
//                AlertDialog alert = builder.create();
//                alert.show();
//
//            }
//
//        });

            }
        });
    }

            public void setDeleteButton(ImageButton deleteButton, int i, ArrayList<String> imageIDs) {
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GalleryViewDialog dialog = new GalleryViewDialog(ImageActivity.this, galleryDao, galleryFolder, galleryadapter, image, i, ImageActivity.this);
                        dialog.show();
                    }
                });
            }

            public void createImageView(PhotoView image, ArrayList<String> imgIDs, int imgNum) {
                Bitmap bmp = BitmapFactory.decodeFile(imgIDs.get(imgNum)); //to save memory
                bmp = Bitmap.createScaledBitmap(bmp, 600, bmp.getHeight() / (bmp.getWidth() / 600), false);

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

        }