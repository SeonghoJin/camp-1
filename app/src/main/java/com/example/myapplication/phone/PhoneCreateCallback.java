package com.example.myapplication.phone;

@FunctionalInterface
public interface PhoneCreateCallback {
    void start(String number, String lastName, String firstName);
}
