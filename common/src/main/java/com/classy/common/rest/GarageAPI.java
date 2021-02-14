package com.classy.common.rest;

import com.classy.common.entities.Garageinfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GarageAPI {

    @GET("WypPzJCt")
    Call<Garageinfo> loadGarage();
}