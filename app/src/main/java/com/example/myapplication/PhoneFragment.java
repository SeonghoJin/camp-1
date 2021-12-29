package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.phone.ConcretePhoneNumberDatabase;
import com.example.myapplication.phone.PhoneNumber;
import com.example.myapplication.phone.PhoneNumberDao;
import com.example.myapplication.phone.PhoneNumberDatabase;
import com.example.myapplication.phone.PhoneNumberViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class PhoneFragment extends Fragment {

    PhoneNumberDao phoneNumberDao;
    List<PhoneNumber> phoneNumbers;
    protected RecyclerView recyclerView;
    protected PhoneNumberViewAdapter phoneNumberViewAdapter;
    protected RecyclerView.LayoutManager layoutManager;
    protected Button onCreateButton;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        initPhoneFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.phone_fragment, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        phoneNumberViewAdapter = new PhoneNumberViewAdapter(phoneNumbers);
        recyclerView.setAdapter(phoneNumberViewAdapter);
        recyclerView.setLayoutManager(layoutManager);
        onCreateButton = rootView.findViewById(R.id.create_phone_number_button);
        onCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(R.layout.phone_number_insert_view)
                        .create().show();
            }
        });
        return rootView;
    }

    private void initPhoneFragment(){
        phoneNumberDao = ConcretePhoneNumberDatabase.getDatabase(this.getContext());
        phoneNumbers = phoneNumberDao.getAll();
    }


}
