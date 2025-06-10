package com.devdroid.task6.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "user")

public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)

    public int id;

    public String fullName;
    public String email;
    public String mobile;
    public String address;
    public String password;

    public User(String address, String email, String fullName, String mobile, String password) {
        this.address = address;
        this.email = email;
        this.fullName = fullName;
        this.mobile = mobile;
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

