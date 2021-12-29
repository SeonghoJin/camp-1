package com.example.myapplication.phone;

public class PhoneCreateCallback {
    PhoneNumberViewAdapter phoneNumberViewAdapter;
    PhoneNumberDao phoneNumberDao;

    public PhoneCreateCallback(PhoneNumberViewAdapter phoneNumberViewAdapter, PhoneNumberDao phoneNumberDao){
        this.phoneNumberViewAdapter = phoneNumberViewAdapter;
        this.phoneNumberDao = phoneNumberDao;
    }

    public void start(String number, String lastName, String firstName){
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.phoneNumber = number;
        phoneNumber.lastName = lastName;
        phoneNumber.firstName = firstName;
        phoneNumberViewAdapter.insert(phoneNumber);
        phoneNumberDao.insert(phoneNumber);
        phoneNumberViewAdapter.notifyDataSetChanged();
    }
}
