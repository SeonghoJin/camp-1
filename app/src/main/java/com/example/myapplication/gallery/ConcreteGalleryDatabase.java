package com.example.myapplication.gallery;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;

public class ConcreteGalleryDatabase {


    public static GalleryDao getDatabase(@NonNull Context context){
            return Room.databaseBuilder(context,
                    GalleryDatabase.class,
                    "GalleryFolder"
            ).allowMainThreadQueries().build().GalleryDao();

        }
    }
