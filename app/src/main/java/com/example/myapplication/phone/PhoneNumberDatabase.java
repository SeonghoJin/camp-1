package com.example.myapplication.phone;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PhoneNumber.class}, version = 1)
public abstract class PhoneNumberDatabase extends RoomDatabase {
    public abstract PhoneNumberDao phoneNumberDao();
}
