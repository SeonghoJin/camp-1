package com.example.myapplication.gallery;


import static com.example.myapplication.gallery.TakePicture.dispatchTakePictureIntent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.GalleryFolderActivity;
import com.example.myapplication.GalleryImageAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FolderDialog extends Dialog {
    private Context context;
    private GalleryDao galleryDao;
    private List<GalleryFolder> galleryFolders;
    private GalleryImageAdapter imageAdapter;
    private GridView gridView;
    private TextView noImage;
    private int position;
    private String imageFilePath;
    private Activity activity;

    public FolderDialog(Context context,
                        GalleryDao galleryDao,
                        List<GalleryFolder> galleryFolders,
                        GalleryImageAdapter imageAdapter,
                        GridView gridView, TextView noImage,
                        int position,
                        String imageFilePath,
                        Activity activity) {
        super(context);
        this.context = context;
        this.galleryDao = galleryDao;
        this.galleryFolders = galleryFolders;
        this.imageAdapter = imageAdapter;
        this.gridView = gridView;
        this.noImage = noImage;
        this.position = position;
        this.imageFilePath = imageFilePath;
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.folder_dialog);

        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button deletebutton = findViewById(R.id.delete);
        Button camerabutton = findViewById(R.id.camera);

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryDao.deleteFolder(galleryFolders.get(position));
                imageAdapter.delete(position);
                gridView.clearChoices();
                gridView.setAdapter(imageAdapter);

                if(galleryFolders.size() == 0){
                    noImage.setVisibility(View.VISIBLE);
                }
                else{
                    noImage.setVisibility(View.GONE);
                }
                dismiss();
            }

        });
        camerabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageFilePath = dispatchTakePictureIntent(activity);
                galleryFolders.get(position).images.add(imageFilePath);
                galleryDao.updateFolders(galleryFolders.get(position));
//                imageAdapter.notifyDataSetChanged();
//                galleryDao.updateFolders(galleryFolders.get(position));
                dismiss();
            }
        });
    }
    


}

