package com.example.myapplication.phone;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class PhoneCreateDialog extends BottomSheetDialog {

    private Context context;

    Button cancelButton;
    Button completeButton;
    ImageView defaultProfileImage;
    TextView defaultTextView;
    EditText firstNameEditText;
    EditText lastNameEditText;
    EditText phoneNumberEditText;
    Boolean existsFirstName;
    Boolean existsLastName;
    Boolean existsPhoneNumber;
    PhoneCreateCallback phoneCreateCallback;

    public PhoneCreateDialog(Context context) {
       super(context);
       this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.phone_number_create_bottom_sheet_view);
        BottomSheetBehavior bottomSheetBehavior = this.getBehavior();
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        initDialog();
        addEventListeners(phoneCreateCallback);
    }

    public void setOnComplete(PhoneCreateCallback phoneCreateCallback){
        this.phoneCreateCallback = phoneCreateCallback;
    }

    private void initDialog(){
        completeButton = findViewById(R.id.complete_action);
        System.out.println(completeButton);
        cancelButton = findViewById(R.id.cancel_action);
        defaultProfileImage = findViewById(R.id.default_profile_image);
        defaultTextView = findViewById(R.id.default_profile_word);
        firstNameEditText = findViewById(R.id.input_first_name);
        lastNameEditText = findViewById(R.id.input_last_name);
        phoneNumberEditText = findViewById(R.id.input_phone_number);
    }

    public BottomSheetDialog addEventListeners(PhoneCreateCallback callback){
        this.completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();
                callback.start(phoneNumber, lastName, firstName);
                dismiss();
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

            @Override
            public void afterTextChanged(Editable editable) {
                System.out.println(editable.toString());
            }
        });

        this.lastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        this.phoneNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return this;
    }

}