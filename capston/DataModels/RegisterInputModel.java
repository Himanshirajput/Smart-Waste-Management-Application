package com.example.capston.DataModels;

public class RegisterInputModel {
    String userName;
    String firstName;
    String lastName;
    String mobile;
    String email;
    String password;
    String aadhar;
    String rfID;

    public RegisterInputModel(String userName, String firstName, String lastName, String mobile, String email, String password, String aadhar, String rfID) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.aadhar = aadhar;
        this.rfID = rfID;
    }
}
