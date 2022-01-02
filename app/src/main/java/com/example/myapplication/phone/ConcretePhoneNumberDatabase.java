package com.example.myapplication.phone;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Room;

import java.util.Arrays;
import java.util.Date;

public class ConcretePhoneNumberDatabase {

    public static PhoneNumberDao getDatabase(@NonNull Context context){
        return Room.databaseBuilder(context,
                PhoneNumberDatabase.class,
                "phone-number"
        ).allowMainThreadQueries().build().phoneNumberDao();
    }

}
