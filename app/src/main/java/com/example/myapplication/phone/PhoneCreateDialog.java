package com.example.myapplication.phone;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

public class PhoneCreateDialog {

    private Context context;

    public PhoneCreateDialog(Context context) {
        this.context = context;
    }

    public void show(PhoneCreateCallback phoneCreateCallback) {

        final Dialog dlg = new Dialog(context);

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setContentView(R.layout.phone_number_insert_view);
        dlg.show();

        final EditText phoneNumber = dlg.findViewById(R.id.input_phone_number);
        final EditText lastName = dlg.findViewById(R.id.input_last_name);
        final EditText firstName = dlg.findViewById(R.id.input_first_name);
        final Button button = dlg.findViewById(R.id.on_create_phone_number_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneCreateCallback.start(
                        phoneNumber.getText().toString(),
                        lastName.getText().toString(),
                        firstName.getText().toString()
                );
                dlg.dismiss();
            }
        });

    }
}