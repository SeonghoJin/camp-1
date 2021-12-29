package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GalleryFragment extends Fragment {


    //Todo: Add image in res/drawable and make integer array
    private Integer[] imageIDs = {
            R.drawable.view01,
            R.drawable.view02,
            R.drawable.view03,
            R.drawable.view04,
            R.drawable.view05,
            R.drawable.view06,
            R.drawable.view07,
            R.drawable.view08,
            R.drawable.view09,
            R.drawable.view10,
            R.drawable.view11,
            R.drawable.view12,
            R.drawable.view13,
            R.drawable.view14,
            R.drawable.view15,
            R.drawable.view16,
            R.drawable.view17,
            R.drawable.view18,
            R.drawable.view19,
    };

    protected DisplayMetrics mMetrics;
    protected Context context;
    protected GalleryImageAdapter imageAdapter;


    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.gallery_fragment, container, false);

        context = container.getContext();
        mMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(mMetrics);

        GridView gridView = (GridView) rootView.findViewById(R.id.gridView);
        imageAdapter = new GalleryImageAdapter(context, imageIDs, mMetrics);
        gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ImageActivity.class);
                intent.putExtra("ImageValue", imageIDs[i].toString());


                startActivity(intent);
            }
        });

        return rootView;
    }

}