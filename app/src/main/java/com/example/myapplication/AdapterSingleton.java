package com.example.myapplication;

import java.util.ArrayList;

public class AdapterSingleton {

    private static AdapterSingleton adapterSingleton;
    private ArrayList<String> imagesIDs;
    private ImageAdapter imageAdapter;


    private AdapterSingleton(ArrayList<String> imagesIDs, ImageAdapter imageAdapter){
        this.imagesIDs = imagesIDs;
        this.imageAdapter = imageAdapter;
    }

    public static AdapterSingleton initInstance(ArrayList<String> imagesIDs, ImageAdapter imageAdapter){
        if(adapterSingleton == null){
            adapterSingleton = new AdapterSingleton(imagesIDs, imageAdapter);
        }

        return adapterSingleton;
    }

    public static AdapterSingleton getInstance() throws Exception {
        if(adapterSingleton == null){
            throw new Exception("Not initialized");
        }

        return adapterSingleton;
    }

    public ImageAdapter getAdapter(){
        return this.imageAdapter;
    }


}
