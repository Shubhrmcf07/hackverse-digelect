package com.example.digilect.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Party {

    @SerializedName("name")
    private String name;
    @SerializedName("logo")
    private String logo;
    @SerializedName("partyId")
    private String partyId;
    @SerializedName("numSeats")
    private int numSeats;
    @SerializedName("electionsContesting")
    private List<String> electionsContesting=null;
    public String getName() {
        return name;
    }
    public List<String> getElectionsContesting() {
        return electionsContesting;
    }
    public String getLogo() {
        return logo;
    }
    public int getNumSeats() {
        return numSeats;
    }
    public String getPartyId() {
        return partyId;
    }
}
