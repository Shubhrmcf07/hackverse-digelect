package com.example.digilect.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel {
    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private List<Election> elections = null;
    public int getStatus() {
        return status;
    }
    public List<Election> getElections() {
        return elections;
    }
}
