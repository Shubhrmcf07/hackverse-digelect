package com.example.digilect.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseVoter {
    @SerializedName("data")
    private Voter voter;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private int status;
    public Voter getVoter() {
        return voter;
    }
    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
}
