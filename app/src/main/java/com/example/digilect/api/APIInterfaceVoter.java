package com.example.digilect.api;

import com.example.digilect.models.ResponseVoter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterfaceVoter {
    @GET("verifyUserCreds")
    Call<ResponseVoter> getVoter(@Query("voterID") String voterID, @Query("dob") String dob, @Query("electionId") String electionId);
}
