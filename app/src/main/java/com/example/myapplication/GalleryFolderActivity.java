package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
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
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.gallery.ConcreteGalleryDatabase;
import com.example.myapplication.gallery.GalleryDao;
import com.example.myapplication.gallery.GalleryFolder;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GalleryFolderActivity extends Activity {


    static final int REQUEST_TAKE_PHOTO = 1;

    GalleryDao galleryDao;
    List<GalleryFolder> galleryFolders;


    protected DisplayMetrics mMetrics;
    protected Context context;
    protected FolderImagesAdapter imageAdapter;
    protected ArrayList<String> imageIDs;

    ImageButton cameraButton;
    String imageFilePath;
    GridView gridView;
    GalleryFolder galleryFolder;
    int key;
    String folderName;

    TextView folderText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_folder);
        context = getApplicationContext();

        Intent intent = getIntent();
        key = intent.getIntExtra("key", -1);
        folderName = intent.getStringExtra("foldername");


        galleryDao = ConcreteGalleryDatabase.getDatabase(getApplicationContext());
        galleryFolders = galleryDao.loadAllFolders();
        galleryFolder = galleryDao.getGalleryFolderbyKey(key);
        if(galleryFolder.images == null){
            galleryFolder.images = new ArrayList<String>(0);
        }

        mMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(mMetrics);

        makegridview(context, galleryFolder);

        cameraButton = findViewById(R.id.camerabutton);
        cameraButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        return;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
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

//        imageAdapter.insert(imageFilePath);
        galleryFolder.images.add(imageFilePath);
        imageAdapter.notifyDataSetChanged();
        galleryDao.updateFolders(galleryFolder);

        gridView.setAdapter(imageAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        galleryFolders = galleryDao.loadAllFolders();
        galleryFolder = galleryFolders.get(key);
        if(galleryFolder.images == null){
            galleryFolder.images = new ArrayList<String>(0);
        }
        imageAdapter = new FolderImagesAdapter(context, galleryFolder.images, mMetrics);
        gridView.setAdapter(imageAdapter);
        makegridview(context, galleryFolder);
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

    public void makegridview(Context context, GalleryFolder galleryFolder){

        imageIDs = galleryFolder.images;

        gridView = (GridView) findViewById(R.id.imagegridView);
        folderText = (TextView)findViewById(R.id.galleryName);
        folderText.setText(folderName);

        imageAdapter = new FolderImagesAdapter(context, imageIDs, mMetrics);

        gridView.setAdapter(imageAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
                intent.putExtra("key", galleryFolder.id);
                intent.putExtra("imageNum", i);
                startActivity(intent);
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GalleryFolderActivity.this);
                builder.setMessage("지우시겠습니까?")
                        .setCancelable(true)
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                //Adapter
                                imageAdapter.delete(i);
                                galleryDao.updateFolders(galleryFolder);
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

//    public ArrayList<String> getPathOfAllImg() {
//
//        ArrayList<String> fileList = new ArrayList<>();
//
//        Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        String[] projection = { MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME };
//
//        Cursor cursor = getContentResolver().query(uri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED + " desc");
//        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//        while (cursor.moveToNext())
//        {
//            String absolutePathOfImage = cursor.getString(columnIndex);
//
//            if (!TextUtils.isEmpty(absolutePathOfImage))
//            {
//
//                fileList.add(absolutePathOfImage);
//            }
//
//        }
//
//        return fileList;
//    }


    //TODO: Firstly, get intent from gallery fragment. 2. show gridView (from activity_galley_folder), then add camera function
        //TODO: When we take pictures, update database, then add to the gridView. (Add insert method to adapter)
        //TODO: Change jpg file to JSON file.
    }