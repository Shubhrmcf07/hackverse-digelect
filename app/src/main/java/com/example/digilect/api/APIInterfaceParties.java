package com.example.digilect.api;

import com.example.digilect.models.ResponseParty;
import com.example.digilect.models.ResponseVoter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterfaceParties {
    @GET("verifyUserCreds")
    Call<ResponseParty> getPartiesList(@Query("electionId") String electionId);
}
