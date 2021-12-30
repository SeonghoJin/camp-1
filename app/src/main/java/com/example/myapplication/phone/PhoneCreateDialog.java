package com.example.myapplication.phone;

import android.content.Context;
import android.content.res.Resources;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.myapplication.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Arrays;

public class PhoneCreateDialog extends BottomSheetDialog {

    Button cancelButton;
    Button completeButton;
    ImageView defaultProfileImage;
    TextView defaultTextView;
    EditText firstNameEditText;
    EditText lastNameEditText;
    EditText phoneNumberEditText;
    Boolean existsFirstName = false;
    Boolean existsLastName = false;
    Boolean existsPhoneNumber = false;
    PhoneCreateCallback phoneCreateCallback;
    Name name;

    public PhoneCreateDialog(Context context) {
       super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.phone_number_create_bottom_sheet_view);
        BottomSheetBehavior bottomSheetBehavior = this.getBehavior();
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        initDialog();
        addEventListeners(phoneCreateCallback);
        onInputTextChanged(new Boolean[]{existsPhoneNumber, existsLastName, existsPhoneNumber});
        onNameChanged(name);
    }

    public void setOnComplete(PhoneCreateCallback phoneCreateCallback){
        this.phoneCreateCallback = phoneCreateCallback;
    }

    private void initDialog(){
        completeButton = findViewById(R.id.complete_action);
        cancelButton = findViewById(R.id.cancel_action);
        defaultProfileImage = findViewById(R.id.default_profile_image);
        defaultTextView = findViewById(R.id.default_profile_word);
        firstNameEditText = findViewById(R.id.input_first_name);
        lastNameEditText = findViewById(R.id.input_last_name);
        phoneNumberEditText = findViewById(R.id.input_phone_number);
        name = new Name(firstNameEditText, lastNameEditText);
    }

    public BottomSheetDialog addEventListeners(PhoneCreateCallback callback){
        this.completeButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(canClickedCompleteButton(new Boolean[]{existsFirstName, existsLastName, existsPhoneNumber})){
                    String firstName = firstNameEditText.getText().toString();
                    String lastName = lastNameEditText.getText().toString();
                    String phoneNumber = phoneNumberEditText.getText().toString();
                    callback.start(phoneNumber, lastName, firstName);
                    dismiss();
                }
            }
        });

        this.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        this.firstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void afterTextChanged(Editable editable) {
                existsFirstName = editable.toString().length() > 0;
                onInputTextChanged(new Boolean[]{existsPhoneNumber, existsLastName, existsFirstName});
                onNameChanged(name);
            }
        });

        this.lastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void afterTextChanged(Editable editable) {
                existsLastName = editable.toString().length() > 0;
                onInputTextChanged(new Boolean[]{existsPhoneNumber, existsLastName, existsFirstName});
                onNameChanged(name);
            }
        });

        this.phoneNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void afterTextChanged(Editable editable) {
                existsPhoneNumber = editable.toString().length() > 0;
                onInputTextChanged(new Boolean[]{existsPhoneNumber, existsLastName, existsFirstName});
            }
        });

        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void onInputTextChanged(Boolean[] booleans){
        if(canClickedCompleteButton(booleans)){
            enableCompleteButton();
        } else {
            unableCompleteButton();
        }
    }

    private void onNameChanged(Name name) {
        try {
            String ellipseName = name.getEllipseName();
            int currentEllipseNameLength = ellipseName.length();

            if(currentEllipseNameLength == 0){
                setVisibleDefaultProfileImage();
                setUnVisibleDefaultProfileTextView();
                return;
            }

            if(currentEllipseNameLength == 1) {
                setUnVisibleDefaultProfileImage();
                setVisibleDefaultProfileTextView();
                defaultTextView.setText(ellipseName);
                return;
            }

            if(currentEllipseNameLength == 2){
                defaultTextView.setText(ellipseName);
                return;
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Boolean canClickedCompleteButton(Boolean[] booleans){
        return Arrays.stream(booleans).reduce(((total, currentBoolean) -> {
            return total | currentBoolean;
        })).get();
    }

    private void enableCompleteButton(){
        completeButton.setTextColor(getResource().getColor(R.color.iphone_links));
    }


    private void unableCompleteButton(){
        completeButton.setTextColor(getResource().getColor(R.color.iphone_settings));
    }

    private void setVisibleDefaultProfileImage(){
        this.defaultProfileImage.setVisibility(View.VISIBLE);
    }

    private void setUnVisibleDefaultProfileImage(){
        this.defaultProfileImage.setVisibility(View.INVISIBLE);
    }

    private void setVisibleDefaultProfileTextView(){
        this.defaultTextView.setVisibility(View.VISIBLE);
    }

    private void setUnVisibleDefaultProfileTextView(){
        this.defaultTextView.setVisibility(View.INVISIBLE);
    }


    protected Resources getResource(){
        return getContext().getResources();
    }
}