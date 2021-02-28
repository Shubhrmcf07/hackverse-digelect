package com.example.digilect.api;

import com.example.digilect.models.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
public interface APIInterfaceElections {
    @GET("getElectionsList")
    Call<ResponseModel> getElectionsList();
}
