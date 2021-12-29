package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
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

    DisplayMetrics mMetrics;
    Context context;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.gallery_fragment, container, false);

        View v = inflater.inflate(R.layout.gallery_fragment, container, false);

        context = container.getContext();

        GridView gridView = (GridView) v.findViewById(R.id.gridView);
        ImageAdapter imageAdapter = new ImageAdapter(context, imageIDs);
        gridView.setAdapter(imageAdapter);
        mMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(mMetrics);
//        gridView.setOnItemClickListener(gridviewOnItemClickListener);

        return v;
    }



    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private Integer[] imageIDs;

        public ImageAdapter(Context context, Integer[] imageIDs){
            this.context = context;
            this.imageIDs = imageIDs;
        }
        @Override
        public int getCount() {
//            return (null ! = imageIDs) ? imageIDs.length : 0;
            return imageIDs.length;
        }

        @Override
        public Object getItem(int i) {
            return imageIDs[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        //creating new imageView
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ImageView imageView;

            int rowWidth = (mMetrics.widthPixels) / 3; // ??

            if(null != convertView){
                imageView = (ImageView)convertView;
            }else{

                Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), imageIDs[i]); //to save memory
                bmp = Bitmap.createScaledBitmap(bmp, 300,300,false);
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(rowWidth, rowWidth));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setPadding(1,1,1,1);
                imageView.setImageBitmap(bmp);
//                imageView.setAdjustViewBounds((true));

//                ImageClickListener imageViewClickListener = new ImageClickListener(context, imageIDs[i]);
//                imageView.setOnClickListener(imageViewClickListener);

            }
            imageView.setImageResource(imageIDs[i]);
            return imageView;




        }
    }

//    public class ImageClickListener implements View.OnClickListener {
//        private Context context;
//        private int imageID;
//
//        public ImageClickListener(Context context, int imageID){
//            this.context = context;
//            this.imageID = imageID;
//
//        }
//
//        @Override
//        public void onClick(View view) {
//            Intent intent = new Intent(context, ImageActivity.class);
//
//        }
//    }
}
