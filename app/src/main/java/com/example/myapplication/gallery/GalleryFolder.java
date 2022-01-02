package com.example.myapplication.gallery;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


import java.util.ArrayList;


@Entity
public class GalleryFolder {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "folder_name")
    public String folderName;

    @ColumnInfo(name = "images")
    public ArrayList<String> images;
}
