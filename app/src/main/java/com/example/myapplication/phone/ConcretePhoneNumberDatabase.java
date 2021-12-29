package com.example.myapplication.phone;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Room;

import java.util.Arrays;
import java.util.Date;

public class ConcretePhoneNumberDatabase {
    public static PhoneNumberDao database = null;

    public static PhoneNumberDao getDatabase(@NonNull Context context){
        if(database == null){
            database = Room.databaseBuilder(context,
                    PhoneNumberDatabase.class,
                    "phone-number"
            ).allowMainThreadQueries().build().phoneNumberDao();

            database.deletePhoneNumberTable();

            PhoneNumber phoneNumber1 = new PhoneNumber();
            phoneNumber1.phoneNumber = "010-4996-1256";
            phoneNumber1.firstName = "seong ho";
            phoneNumber1.lastName = "jin";

            PhoneNumber phoneNumber2 = new PhoneNumber();
            phoneNumber2.phoneNumber = "010-1-1256";
            phoneNumber2.firstName = "seong ho";
            phoneNumber2.lastName = "jin";

            PhoneNumber phoneNumber3 = new PhoneNumber();
            phoneNumber3.phoneNumber = "010-2-1256";
            phoneNumber3.firstName = "seong ho";
            phoneNumber3.lastName = "jin";

            PhoneNumber phoneNumber4 = new PhoneNumber();
            phoneNumber4.phoneNumber = "010-3-1256";
            phoneNumber4.firstName = "seong ho";
            phoneNumber4.lastName = "jin";

            PhoneNumber phoneNumber5 = new PhoneNumber();
            phoneNumber5.phoneNumber = "010-4-1256";
            phoneNumber5.firstName = "seong ho";
            phoneNumber5.lastName = "jin";

            PhoneNumber phoneNumber6 = new PhoneNumber();
            phoneNumber6.phoneNumber = "010-5-1256";
            phoneNumber6.firstName = "seong ho";
            phoneNumber6.lastName = "jin";

            PhoneNumber phoneNumber7 = new PhoneNumber();
            phoneNumber7.phoneNumber = "010-6-1256";
            phoneNumber7.firstName = "seong ho";
            phoneNumber7.lastName = "jin";

            PhoneNumber phoneNumber8 = new PhoneNumber();
            phoneNumber8.phoneNumber = "010-7-1256";
            phoneNumber8.firstName = "seong ho";
            phoneNumber8.lastName = "jin";

            PhoneNumber[] phoneNumbers = {
                    phoneNumber1,
                    phoneNumber2,
                    phoneNumber3,
                    phoneNumber4,
                    phoneNumber5,
                    phoneNumber6,
                    phoneNumber7,
                    phoneNumber8
            };

            for(int i = 0; i < phoneNumbers.length; i++){
                database.insert(phoneNumbers[i]);
            }
        }

        return database;
    }

}
