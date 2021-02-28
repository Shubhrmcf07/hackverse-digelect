package com.example.digilect.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseParty {

    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private List<Party> parties = null;
    public int getStatus() {
        return status;
    }
    public List<Party> getParties() {
        return parties;
    }
}
