package com.example.myapplication;

import android.Manifest;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GalleryFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);

            //get permission
            int permission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int permission2 = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
            int permission3 = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA);
            if (permission == PackageManager.PERMISSION_DENIED || permission2 == PackageManager.PERMISSION_DENIED || permission3 == PackageManager.PERMISSION_DENIED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions( new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
                }
                return;
            }
    }


        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grandResults) {
            if(requestCode == 1000) {
                boolean check_result = true;
                for (int result : grandResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        check_result = false; break;
                    }
                }
                if(check_result == true) {

                } else {
                    System.out.println("nopermission");; }
            }
        }



    protected DisplayMetrics mMetrics;
    protected Context context;
    protected GalleryImageAdapter imageAdapter;
    protected ArrayList<String> imageIDs;
    File file;

    ImageButton camerabutton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.gallery_fragment, container, false);
        context = getContext();
        mMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(mMetrics);


        makegridview(context, rootView);

        camerabutton = rootView.findViewById(R.id.camerabutton);
        camerabutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
                makegridview(context, rootView);
            }
        });


        return rootView;
    }

    public void makegridview(Context context, View view){
        imageIDs = getPathOfAllImg();
        System.out.println("makegridview");
        System.out.println(imageIDs);


        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        imageAdapter = new GalleryImageAdapter(context, imageIDs, mMetrics);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new
                                                AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("flag2");
                Intent intent = new Intent(getActivity(), ImageActivity.class);
                intent.putExtra("ImageValue", imageIDs.get(i).toString());
                intent.putExtra("ImageNum", Integer.toString(i));
                intent.putStringArrayListExtra("ImageIDs", imageIDs);
                startActivity(intent);
            }
        });
    }
    public ArrayList<String> getPathOfAllImg() {

        ArrayList<String> fileList = new ArrayList<>();

        Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME };

        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED + " desc");
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        int columnDisplayname = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);

        int lastIndex;
        while (cursor.moveToNext())
        {
            String absolutePathOfImage = cursor.getString(columnIndex);
            String nameOfFile = cursor.getString(columnDisplayname);
            lastIndex = absolutePathOfImage.lastIndexOf(nameOfFile);
            lastIndex = lastIndex >= 0 ? lastIndex : nameOfFile.length() - 1;

            if (!TextUtils.isEmpty(absolutePathOfImage))
            {
                fileList.add(absolutePathOfImage);
            }
            System.out.println(fileList);
        }

        return fileList;
    }

//    static final int REQUEST_IMAGE_CAPTURE = 1;
//
//    private void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        }
//    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            //Bundle extras = data.getExtras();
            //Bitmap imageBitmap = (Bitmap) extras.get("data");
            //mImageView.setImageBitmap(imageBitmap);
            galleryAddPic();
        }
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        String storageDir = Environment.getExternalStorageDirectory() + "/DCIM/Camera";
        System.out.println(storageDir);
        File image = File.createTempFile(
                "example",  /* prefix */
                ".jpg",         /* suffix */
                new File(storageDir)      /* directory */
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        System.out.println("flag");
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                System.out.println("flag");
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID+".fileprovider", photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID+".fileprovider", f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

}