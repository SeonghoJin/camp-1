package com.example.myapplication.phone;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.myapplication.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Arrays;

public class PhoneCreateDialogFragment extends BottomSheetDialogFragment {

    View rootView;
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       rootView = inflater.inflate(R.layout.phone_create_dialog_fragment, container, false);
        initDialog(rootView);
        addEventListeners(phoneCreateCallback);
        onInputTextChanged(new Boolean[]{existsPhoneNumber, existsLastName, existsPhoneNumber});
        onNameChanged(name);
       return rootView;
    }

    private void initDialog(View view){
        completeButton = view.findViewById(R.id.complete_action);
        cancelButton = view.findViewById(R.id.cancel_action);
        defaultProfileImage = view.findViewById(R.id.default_profile_image);
        defaultTextView = view.findViewById(R.id.default_profile_word);
        firstNameEditText = view.findViewById(R.id.input_first_name);
        lastNameEditText = view.findViewById(R.id.input_last_name);
        phoneNumberEditText = view.findViewById(R.id.input_phone_number);
        name = new Name(firstNameEditText, lastNameEditText);
    }

    @Override
    public int getTheme() {
        return R.style.BottomSheetDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(dialogInterface -> {
            BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
            setupRatio(bottomSheetDialog);
        });
        return dialog;
    }

    public void setOnComplete(PhoneCreateCallback phoneCreateCallback){
        this.phoneCreateCallback = phoneCreateCallback;
    }

    public void addEventListeners(PhoneCreateCallback callback){
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

    private void setupRatio(BottomSheetDialog bottomSheetDialog) {
        FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();
        layoutParams.height = getBottomSheetDialogDefaultHeight();
        bottomSheet.setLayoutParams(layoutParams);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        behavior.setPeekHeight(layoutParams.height);
    }

    private int getBottomSheetDialogDefaultHeight() {
        return getWindowHeight() * 95 / 100;
    }

    private int getWindowHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
}
