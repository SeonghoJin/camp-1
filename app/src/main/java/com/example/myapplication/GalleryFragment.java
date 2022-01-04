package com.example.myapplication;



import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.net.Uri;
import android.os.Bundle;


import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;



import com.example.myapplication.gallery.ConcreteGalleryDatabase;
import com.example.myapplication.gallery.FolderDialog;
import com.example.myapplication.gallery.GalleryDao;
import com.example.myapplication.gallery.GalleryFolder;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment {

    static final int REQUEST_TAKE_PHOTO = 1;

    GalleryDao galleryDao;
    List<GalleryFolder> galleryFolders;
    GalleryFolder galleryFolder;


    protected DisplayMetrics mMetrics;
    protected Context context;
    protected GalleryImageAdapter imageAdapter;
    protected ArrayList<String> imageIDs;

    ImageButton addButton;
    String imageFilePath;
    GridView gridView;
    ViewGroup rootView;
    TextView noFolder;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.gallery_fragment, container, false);
        context = getContext();
        mMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(mMetrics);


        galleryDao = ConcreteGalleryDatabase.getDatabase(this.getContext());
//        galleryDao.deleteall();
        galleryFolders = galleryDao.loadAllFolders();

        //NO image
        noFolder = (TextView) rootView.findViewById(R.id.nofolder);

        if(galleryFolders.size() == 0){
            noFolder.setVisibility(View.VISIBLE);
        }
        else{
            noFolder.setVisibility(View.GONE);
        }

        makegridview(context, rootView);

        addButton = rootView.findViewById(R.id.addbutton);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                CreateFolderDialog dialog = new CreateFolderDialog(getActivity(), galleryDao, galleryFolders, imageAdapter, gridView, noFolder);
                dialog.show();
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        galleryFolders = galleryDao.loadAllFolders();
        imageAdapter = new GalleryImageAdapter(context, galleryFolders, mMetrics);
        gridView.setAdapter(imageAdapter);
        updateView();


    }


    public void makegridview(Context context, View view){
        gridView = (GridView) view.findViewById(R.id.foldergridview);

        imageAdapter = new GalleryImageAdapter(context, galleryFolders, mMetrics);
        gridView.setAdapter(imageAdapter);
        updateView();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), GalleryFolderActivity.class);

                //TODO: If click the folder, send the GalleryFolder name and images. (Using database)
                String name = galleryFolders.get(i).folderName;
                intent.putExtra("key", galleryFolders.get(i).id);
                intent.putExtra("foldername", galleryFolders.get(i).folderName);
                startActivity(intent);
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(galleryFolders.get(i).images == null){
                    galleryFolders.get(i).images = new ArrayList<String>(0);
                }
                galleryFolder = galleryFolders.get(i);
                FolderDialog dialog = new FolderDialog(getActivity(), galleryDao, galleryFolders, imageAdapter, gridView, noFolder, i, imageFilePath, getActivity());
                dialog.show();


                return true;
            }
        });
    }


    public void updateView(){
        if(galleryFolders.size() == 0){
            noFolder.setVisibility(View.VISIBLE);
        }
        else{
            noFolder.setVisibility(View.GONE);
        }
    }
}
