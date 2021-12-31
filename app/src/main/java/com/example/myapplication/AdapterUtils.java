package com.example.myapplication;

import android.widget.BaseAdapter;

import java.io.File;
import java.util.ArrayList;

public class AdapterUtils {

    static BaseAdapter removeFile(ArrayList<String> array, int position, BaseAdapter adapter){

        String filepath = array.get(position);
        File file = new File(filepath);
        System.out.println(filepath);
        file.delete();
        array.remove(position);
        adapter.notifyDataSetChanged();


        return adapter;
    };
}
