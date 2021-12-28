package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.phone.ConcretePhoneNumberDatabase;
import com.example.myapplication.phone.PhoneNumberDao;
import com.example.myapplication.phone.PhoneNumberDatabase;

public class PhoneFragment extends Fragment {

    PhoneNumberDao phoneNumberDao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.phone_fragment, container, false);
        phoneNumberDao = ConcretePhoneNumberDatabase.getDatabase(this.getContext());
        System.out.println(phoneNumberDao.getAll());
        return rootView;
    }



}
