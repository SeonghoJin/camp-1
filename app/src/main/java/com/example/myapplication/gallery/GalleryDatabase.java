package com.example.myapplication.gallery;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {GalleryFolder.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class GalleryDatabase extends RoomDatabase {
    public abstract GalleryDao GalleryDao();
}
