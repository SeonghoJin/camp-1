package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.gallery.GalleryDao;
import com.example.myapplication.gallery.GalleryFolder;

import java.util.List;
import java.util.Objects;

public class CreateFolderDialog extends Dialog {
    private Context context;
    private GalleryDao galleryDao;
    private List<GalleryFolder> galleryFolders;
    private GalleryImageAdapter imageAdapter;
    private GridView gridView;
    private TextView noImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.folder_create_dialog);

        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button savebutton = findViewById(R.id.save);
        Button cancelbutton = findViewById(R.id.cancel);

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView input = findViewById(R.id.titleinput);
                String value = input.getText().toString();
                GalleryFolder galleryFolder = new GalleryFolder();
                galleryFolder.folderName = value;
                galleryFolder.id = galleryFolders.size();
                galleryDao.insertFolder(galleryFolder);
                imageAdapter.insert(galleryFolder);
                gridView.setAdapter(imageAdapter);

                if(galleryFolders.size() == 0){
                    noImage.setVisibility(View.VISIBLE);
                }
                else{
                    noImage.setVisibility(View.GONE);
                }
                Toast.makeText(getContext(), "앨범이 생성되었습니다.", Toast.LENGTH_LONG).show();
                dismiss();
            }
        });
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public CreateFolderDialog(Context context, GalleryDao galleryDao, List<GalleryFolder> galleryFolders, GalleryImageAdapter imageAdapter, GridView gridView, TextView noImage) {
        super(context);
        this.context = context;
        this.galleryDao = galleryDao;
        this.galleryFolders = galleryFolders;
        this.imageAdapter = imageAdapter;
        this.gridView = gridView;
        this.noImage = noImage;

    }


}

