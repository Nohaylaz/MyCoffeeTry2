package com.example.mycoffeetry;

import android.widget.CheckBox;

public class UserBDD {

    String fullName, userName, email, password, phoneNum, gender;


    public UserBDD() {

    }

    public UserBDD(String fullName, String userName, String email,String phoneNum, String password, String gender) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.password = password;
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
