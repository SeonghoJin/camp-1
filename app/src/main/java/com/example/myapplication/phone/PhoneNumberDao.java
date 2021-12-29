package com.example.myapplication.phone;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PhoneNumberDao {
    @Query("SELECT * FROM PhoneNumber")
    List<PhoneNumber> getAll();

    @Insert
    void insert(PhoneNumber phoneNumbers);

    @Delete
    void delete(PhoneNumber phoneNumber);

    @Query("DELETE FROM PhoneNumber")
    void deletePhoneNumberTable();

}
