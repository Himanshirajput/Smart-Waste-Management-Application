package com.example.capston.DataModels;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("message")
    String message;

    @SerializedName("status")
    int status;

    @SerializedName("data")
    UserData data;

    public User(String message, int status, UserData data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }


    public  class UserData {
        @SerializedName("id")
        int id;

        @SerializedName("userName")
        String userName;

        @SerializedName("firstName")
        String firstName;

        @SerializedName("lastName")
        String lastName;

        @SerializedName("mobile")
        String mobile;

        @SerializedName("email")
        String email;

        @SerializedName("aadhar")
        String aadhar;

        @SerializedName("rewards")
        int rewards;

        @SerializedName("accessToken")
        String accessToken;

        @SerializedName("isMuncipalUser")
        int isMuncipalUser;

        @SerializedName("password")
        String password;

        @SerializedName("rfID")
        String rfID;

        public UserData(int id, String userName, String firstName, String lastName, String mobile, String email, String aadhar, int rewards, String accessToken, int isMuncipalUser, String password, String rfID) {
            this.id = id;
            this.userName = userName;
            this.firstName = firstName;
            this.lastName = lastName;
            this.mobile = mobile;
            this.email = email;
            this.aadhar = aadhar;
            this.rewards = rewards;
            this.accessToken = accessToken;
            this.isMuncipalUser = isMuncipalUser;
            this.password = password;
            this.rfID = rfID;
        }

        public int getId() {
            return id;
        }

        public void setIsMuncipalUser(int isMuncipalUser) {
            this.isMuncipalUser = isMuncipalUser;
        }

        public void setRfID(String rfID) {
            this.rfID = rfID;
        }

        public int getIsMuncipalUser() {
            return isMuncipalUser;
        }

        public String getRfID() {
            return rfID;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAadhar() {
            return aadhar;
        }

        public void setAadhar(String aadhar) {
            this.aadhar = aadhar;
        }

        public int getRewards() {
            return rewards;
        }

        public void setRewards(int rewards) {
            this.rewards = rewards;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public int getMuncipalUser() {
            return isMuncipalUser;
        }

        public void setMuncipalUser(int muncipalUser) {
            isMuncipalUser = muncipalUser;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}


