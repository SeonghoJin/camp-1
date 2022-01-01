package com.example.myapplication.gallery;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GalleryDao {
    @Insert
    public void insertFolder(GalleryFolder galleryFolder);

    @Update
    public void updateFolders(GalleryFolder galleryFolder);

    @Delete
    public void deleteFolder(GalleryFolder galleryFolder);

    @Query("SELECT * FROM galleryFolder")
    public List<GalleryFolder> loadAllFolders();

}
