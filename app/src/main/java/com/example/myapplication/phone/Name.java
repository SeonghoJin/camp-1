package com.example.myapplication.phone;

import android.widget.EditText;

public class Name {
    EditText firstName;
    EditText lastName;

    public Name(EditText firstName, EditText lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getName(){
        return lastName.getText().toString() + firstName.getText().toString();
    }

    public String getEllipseName() throws Exception {
        String currentName = getName();
        if(currentName.length() == 0){
            return "";
        }
        if(currentName.length() == 1){
            return currentName.substring(0, 1);
        }
        if(currentName.length() == 2){
            return currentName.substring(0, 2);
        }
        if(currentName.length() > 2){
            return currentName.substring(0, 1);
        }

        throw new Exception("Can not execute getEllipsesName");
    }

}
