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
        return Name.toEllipseName(getName());
    }

    public static String toEllipseName(String name) throws Exception  {
        if(name.length() == 0){
            return "";
        }
        if(name.length() == 1){
            return name.substring(0, 1);
        }
        if(name.length() == 2){
            return name.substring(0, 2);
        }
        if(name.length() > 2){
            return name.substring(0, 1);
        }
        throw new Exception("Can not execute getEllipsesName");
    }

}
