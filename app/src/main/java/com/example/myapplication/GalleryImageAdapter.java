package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.gallery.FolderItem;
import com.example.myapplication.gallery.GalleryFolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GalleryImageAdapter extends BaseAdapter {
    private DisplayMetrics mMetrics;
    private Context context;
    private List<GalleryFolder> galleryFolders;
    private ArrayList<String> imageIDs;

    public GalleryImageAdapter(Context context, List<GalleryFolder> galleryFolders, DisplayMetrics mMetrics){
        this.context = context;
        this.galleryFolders = galleryFolders;
        this.mMetrics = mMetrics;
    }

    @Override
    public int getCount() {
        return galleryFolders.size();
    }

    @Override
    public Object getItem(int i) {
        return galleryFolders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void insert(GalleryFolder galleryFolder) {
        this.galleryFolders.add(galleryFolder);
        this.notifyDataSetChanged();
    }

    public void delete(int i){
        this.galleryFolders.remove(i);
        this.notifyDataSetChanged();
    }

    @Override
    //creating new imageView
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        FolderItem folderItem = new FolderItem(context);
//        ImageView imageView = (ImageView)convertView;
        imageIDs = galleryFolders.get(i).images;

        int rowWidth = (mMetrics.widthPixels - mMetrics.widthPixels/15) / 3;
        folderItem.setItem(galleryFolders.get(i).folderName, imageIDs, rowWidth);


//        if(null != convertView){
//            imageView = (ImageView)convertView;
//        }else{



//        imageView = new ImageView(context)
        return folderItem;
    }



}
