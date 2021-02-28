package com.example.digilect.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Voter implements Serializable {
    @SerializedName("voterID")
    private String voterID;
    @SerializedName("name")
    private String name;
    @SerializedName("DOB")
    private String dob;
    @SerializedName("photoURL")
    private String photoURL;
    @SerializedName("constituency")
    private String constituency;
    @SerializedName("address")
    private String address;
    @SerializedName("district")
    private String district;
    @SerializedName("state")
    private String state;
    @SerializedName("phone")
    private String phone;
    @SerializedName("gender")
    private int gender;
    public int getGender() {
        return gender;
    }
    public String getVoterID() {
        return voterID;
    }
    public String getName() {
        return name;
    }
    public String getDob() {
        return dob;
    }
    public String getConstituency(){
        return constituency;
    }
    public String getPhotoURL() {
        return photoURL;
    }
    public String getAddress() {
        return address;
    }
    public String getDistrict() {
        return district;
    }
    public String getState() {
        return state;
    }
    public String getPhone() {
        return phone;
    }
}
