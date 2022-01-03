package com.example.myapplication;



import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.net.Uri;
import android.os.Bundle;


import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;



import com.example.myapplication.gallery.ConcreteGalleryDatabase;
import com.example.myapplication.gallery.GalleryDao;
import com.example.myapplication.gallery.GalleryFolder;



import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment {

    static final int REQUEST_TAKE_PHOTO = 1;

    GalleryDao galleryDao;
    List<GalleryFolder> galleryFolders;


    protected DisplayMetrics mMetrics;
    protected Context context;
    protected GalleryImageAdapter imageAdapter;
    protected ArrayList<String> imageIDs;

    ImageButton addButton;
    String imageFilePath;
    GridView gridView;
    ViewGroup rootView;
    TextView noImage;

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
        noImage = (TextView) rootView.findViewById(R.id.noimage);

        if(galleryFolders.size() == 0){
            noImage.setVisibility(View.VISIBLE);
        }
        else{
            noImage.setVisibility(View.GONE);
        }

        makegridview(context, rootView);

        addButton = rootView.findViewById(R.id.addbutton);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                createFolder();
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

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("지우시겠습니까?")
                        .setCancelable(true)
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                //TODO: folder delete
                                galleryDao.deleteFolder(galleryFolders.get(i));
                                imageAdapter.delete(i);
                                gridView.clearChoices();
                                gridView.setAdapter(imageAdapter);
                                updateView();
                            }
                        })
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();


                return true;
            }
        });
    }

    public void createFolder(){
        EditText et = new EditText(getContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("폴더 생성")
                .setMessage("폴더 이름을 입력해주세요")
                .setCancelable(false)
                .setView(et)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String value = et.getText().toString();
                        GalleryFolder galleryFolder = new GalleryFolder();
                        galleryFolder.folderName = value;
                        galleryFolder.id = galleryFolders.size();
                        galleryDao.insertFolder(galleryFolder);
                        imageAdapter.insert(galleryFolder);
                        gridView.setAdapter(imageAdapter);
                        updateView();


                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

            }


    public ArrayList<String> getPathOfAllImg() {

        ArrayList<String> fileList = new ArrayList<>();

        Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME };

        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED + " desc");
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        while (cursor.moveToNext())
        {
            String absolutePathOfImage = cursor.getString(columnIndex);

            if (!TextUtils.isEmpty(absolutePathOfImage))
            {

                fileList.add(absolutePathOfImage);
            }

        }

        return fileList;
    }

    public void updateView(){
        if(galleryFolders.size() == 0){
            noImage.setVisibility(View.VISIBLE);
        }
        else{
            noImage.setVisibility(View.GONE);
        }
    }
}
