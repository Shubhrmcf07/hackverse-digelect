package com.example.digilect.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Election {


    @SerializedName("_id")
    private String id;
    @SerializedName("electionTitle")
    private String electionTitle;
    @SerializedName("constituency")
    private String constituency;
    @SerializedName("partiesContesting")
    private List<Party> parties=null;
    public List<Party> getParties() {
        return parties;
    }
    public String getId() {
        return id;
    }
    public String getElectionTitle() {
        return electionTitle;
    }
    public String getConstituency() {
        return constituency;
    }
}
