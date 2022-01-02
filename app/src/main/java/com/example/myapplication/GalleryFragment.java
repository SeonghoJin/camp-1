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
import android.widget.GridView;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class GalleryFragment extends Fragment {

    static final int REQUEST_TAKE_PHOTO = 1;


    protected DisplayMetrics mMetrics;
    protected Context context;
    protected GalleryImageAdapter imageAdapter;
    protected ArrayList<String> imageIDs;

    ImageButton cameraButton;
    String imageFilePath;
    GridView gridView;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    private int gridViewVerticalPositionWhenThumbnailTapped;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.gallery_fragment, container, false);
        context = getContext();
        mMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(mMetrics);

        makegridview(context, rootView);

        cameraButton = rootView.findViewById(R.id.camerabutton);
        cameraButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        return rootView;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "com.example.android.fileprovider",
                        photoFile);

                 takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }



    @Override public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode != REQUEST_TAKE_PHOTO){
            return;
        }

        imageIDs.add(imageFilePath);
       imageAdapter.notifyDataSetChanged();
       gridView.setAdapter(imageAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        imageIDs.clear();
        imageIDs.addAll(getPathOfAllImg());
        imageAdapter.notifyDataSetChanged();
        gridView.setAdapter(imageAdapter);
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File("/storage/self/primary/DCIM/Camera");

       File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }

    public void makegridview(Context context, View view){
        imageIDs = getPathOfAllImg();
        gridView = (GridView) view.findViewById(R.id.gridView);
        imageAdapter = new GalleryImageAdapter(context, imageIDs, mMetrics);

        gridView.setAdapter(imageAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ImageActivity.class);
                intent.putExtra("ImageValue", imageIDs.get(i));
                intent.putExtra("ImageNum", Integer.toString(i));
                intent.putStringArrayListExtra("ImageIDs", imageIDs);
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
                                System.out.println(imageIDs.size());
                                System.out.println(i);
                                imageAdapter = (GalleryImageAdapter) AdapterUtils.removeFile(imageIDs, i, imageAdapter);

                                gridView.clearChoices();
                                gridView.setAdapter(imageAdapter);
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

    public ArrayList<String> getPathOfAllImg() {

        ArrayList<String> fileList = new ArrayList<>();

        Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME };

        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED + " desc");
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//        int columnDisplayname = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);

//        int lastIndex;
        while (cursor.moveToNext())
        {
            String absolutePathOfImage = cursor.getString(columnIndex);
//            String nameOfFile = cursor.getString(columnDisplayname);
//            lastIndex = absolutePathOfImage.lastIndexOf(nameOfFile);
//            lastIndex = lastIndex >= 0 ? lastIndex : nameOfFile.length() - 1;

            if (!TextUtils.isEmpty(absolutePathOfImage))
            {

                fileList.add(absolutePathOfImage);
            }

        }

        return fileList;
    }


}