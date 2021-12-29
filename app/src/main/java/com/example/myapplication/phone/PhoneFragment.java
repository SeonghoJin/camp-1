package com.example.myapplication.phone;

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

import com.example.myapplication.R;
import com.example.myapplication.phone.ConcretePhoneNumberDatabase;
import com.example.myapplication.phone.PhoneCreateDialog;
import com.example.myapplication.phone.PhoneNumber;
import com.example.myapplication.phone.PhoneNumberDao;
import com.example.myapplication.phone.PhoneNumberViewAdapter;

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
                PhoneCreateDialog phoneCreateDialog = new PhoneCreateDialog(getContext());
                phoneCreateDialog.show(new PhoneCreateCallback(phoneNumberViewAdapter, phoneNumberDao));
            }
        });
        return rootView;
    }

    private void initPhoneFragment(){
        phoneNumberDao = ConcretePhoneNumberDatabase.getDatabase(this.getContext());
        phoneNumbers = phoneNumberDao.getAll();
    }


}
