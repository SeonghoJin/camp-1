package com.example.myapplication.gallery;

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

import com.example.myapplication.FolderImagesAdapter;
import com.example.myapplication.GalleryImageAdapter;
import com.example.myapplication.R;

import java.util.List;
import java.util.Objects;

public class ImageDialog extends Dialog {

    private Context context;
    private GalleryDao galleryDao;
    private List<GalleryFolder> galleryFolders;
    private FolderImagesAdapter imageAdapter;
    private GridView gridView;
    private TextView noImage;
    private int position;

    public ImageDialog(Context context,
                       GalleryDao galleryDao,
                       List<GalleryFolder> galleryFolders,
                       FolderImagesAdapter imageAdapter,
                       GridView gridView, TextView noImage,
                       int position){
        super(context);
        this.context = context;
        this.galleryDao = galleryDao;
        this.galleryFolders = galleryFolders;
        this.imageAdapter = imageAdapter;
        this.gridView = gridView;
        this.noImage = noImage;
        this.position = position;
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
                imageAdapter.delete(position);
                galleryDao.updateFolders(galleryFolders.get(position));
                gridView.clearChoices();
                gridView.setAdapter(imageAdapter);
                updateView(galleryFolders.get(position), noImage);
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
    public void updateView(GalleryFolder galleryFolder, TextView noImage){
        if(galleryFolder.images.size() == 0){
            noImage.setVisibility(View.VISIBLE);
        }
        else{
            noImage.setVisibility(View.GONE);
        }
    }
}
