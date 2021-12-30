package com.example.myapplication.phone;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Call {
    String phoneNumber;

    public Call(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void call(Context context){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }
}
