package com.example.myapplication.phone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class PhoneFragment extends Fragment {

    PhoneNumberDao phoneNumberDao;
    List<PhoneNumber> phoneNumbers;
    protected RecyclerView recyclerView;
    protected PhoneNumberViewAdapter phoneNumberViewAdapter;
    protected RecyclerView.LayoutManager layoutManager;
    protected ImageButton onCreateButton;

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
        phoneNumberViewAdapter = new PhoneNumberViewAdapter(phoneNumbers, getContext());
        recyclerView.setAdapter(phoneNumberViewAdapter);
        recyclerView.setLayoutManager(layoutManager);

        onCreateButton = rootView.findViewById(R.id.create_phone_number_button);
        onCreateButton.setOnClickListener(view -> {
            PhoneCreateDialog phoneCreateDialog = new PhoneCreateDialog(getContext());
            phoneCreateDialog.setOnComplete(
                    (String number, String lastName, String firstName) -> {
                        PhoneNumber phoneNumber = new PhoneNumber();
                        phoneNumber.phoneNumber = number;
                        phoneNumber.lastName = lastName;
                        phoneNumber.firstName = firstName;
                        phoneNumberViewAdapter.insert(phoneNumber);
                        phoneNumberDao.insert(phoneNumber);
                        phoneNumberViewAdapter.notifyDataSetChanged();
                    }
            );
            phoneCreateDialog.show();
        });
        return rootView;
    }

    private void initPhoneFragment(){
        phoneNumberDao = ConcretePhoneNumberDatabase.getDatabase(this.getContext());
        phoneNumbers = phoneNumberDao.getAll();
    }


}
