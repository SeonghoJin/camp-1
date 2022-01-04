package com.example.myapplication.gallery;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

import java.util.Objects;

public class ImageInfoDialog extends Dialog {

    private String folderName;

    public ImageInfoDialog(@NonNull Context context, String folderName) {
        super(context);
        this.folderName = folderName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.folderinfo);
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView infotext = findViewById(R.id.information);
        infotext.setText(folderName);
        TextView canceltext = findViewById(R.id.canceltext);

        canceltext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }.

        });

    }
}
