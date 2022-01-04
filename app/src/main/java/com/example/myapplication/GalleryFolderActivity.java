package com.example.myapplication;


import static com.example.myapplication.gallery.TakePicture.dispatchTakePictureIntent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.gallery.ConcreteGalleryDatabase;
import com.example.myapplication.gallery.GalleryDao;
import com.example.myapplication.gallery.GalleryFolder;
import com.example.myapplication.gallery.ImageDialog;
import com.example.myapplication.gallery.TakePicture;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GalleryFolderActivity extends Activity {


    static final int REQUEST_TAKE_PHOTO = 1;

    GalleryDao galleryDao;
    List<GalleryFolder> galleryFolders;


    protected DisplayMetrics mMetrics;
    protected Context context;
    protected FolderImagesAdapter imageAdapter;
    protected ArrayList<String> imageIDs;

    ImageButton cameraButton;
    String imageFilePath;
    GridView gridView;
    GalleryFolder galleryFolder;
    int key;
    String folderName;

    TextView folderText;
    TextView noImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_folder);
        context = getApplicationContext();

        Intent intent = getIntent();
        key = intent.getIntExtra("key", -1);
        folderName = intent.getStringExtra("foldername");
        noImage = (TextView) findViewById(R.id.noimage);


        galleryDao = ConcreteGalleryDatabase.getDatabase(getApplicationContext());
        galleryFolders = galleryDao.loadAllFolders();
        galleryFolder = galleryDao.getGalleryFolderbyKey(key);
        if(galleryFolder.images == null){
            galleryFolder.images = new ArrayList<String>(0);
        }

        if(galleryFolder.images.size() == 0){
            noImage.setVisibility(View.VISIBLE);
        }
        else{
            noImage.setVisibility(View.GONE);
        }


        mMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(mMetrics);

        makegridview(context, galleryFolder);

        cameraButton = findViewById(R.id.camerabutton);
        cameraButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                imageFilePath = dispatchTakePictureIntent(GalleryFolderActivity.this);
            }
        });
        return;
    }


    @Override public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        TakePicture.onCaptureCallback(requestCode, resultCode, intent, REQUEST_TAKE_PHOTO, () -> {
            galleryFolder.images.add(imageFilePath);
            imageAdapter.notifyDataSetChanged();
            galleryDao.updateFolders(galleryFolder);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        galleryFolders = galleryDao.loadAllFolders();
        galleryFolder = galleryFolders.get(key);
        if(galleryFolder.images == null){
            galleryFolder.images = new ArrayList<String>(0);
        }
        imageAdapter = new FolderImagesAdapter(context, galleryFolder.images, mMetrics);
        gridView.setAdapter(imageAdapter);
        updateView();
        makegridview(context, galleryFolder);
    }


    public void makegridview(Context context, GalleryFolder galleryFolder){

        imageIDs = galleryFolder.images;

        gridView = (GridView) findViewById(R.id.imagegridView);
        folderText = (TextView)findViewById(R.id.galleryName);
        folderText.setText(folderName);

        imageAdapter = new FolderImagesAdapter(context, imageIDs, mMetrics);

        gridView.setAdapter(imageAdapter);
        updateView();


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
                intent.putExtra("key", galleryFolder.id);
                intent.putExtra("imageNum", i);
                startActivity(intent);
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ImageDialog imageDialog = new ImageDialog(GalleryFolderActivity.this,
                        galleryDao,
                        galleryFolder,
                        imageAdapter,
                        gridView,
                        noImage,
                        i);
                imageDialog.show();
//                AlertDialog.Builder builder = new AlertDialog.Builder(GalleryFolderActivity.this);
//                builder.setMessage("지우시겠습니까?")
//                        .setCancelable(true)
//                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int j) {
//                                //Adapter
//                                imageAdapter.delete(i);
//                                galleryDao.updateFolders(galleryFolder);
//                                gridView.clearChoices();
//                                gridView.setAdapter(imageAdapter);
//                                updateView();
//                            }
//                        })
//                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                            }
//                        });
//
//                AlertDialog alert = builder.create();
//                alert.show();


                return true;
            }
        });
    }

    public void updateView(){
        if(galleryFolder.images.size() == 0){
            noImage.setVisibility(View.VISIBLE);
        }
        else{
            noImage.setVisibility(View.GONE);
        }
    }
    }