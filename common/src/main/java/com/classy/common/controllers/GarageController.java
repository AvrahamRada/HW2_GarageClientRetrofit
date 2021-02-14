package com.classy.common.controllers;

import com.classy.common.entities.Garageinfo;
import com.classy.common.rest.GarageAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GarageController implements Callback<Garageinfo> {

    static final String BASE_URL = "https://pastebin.com/raw/";

    private Callback_Garage callback_garage;

    public void start(Callback_Garage callback_garage) {
        this.callback_garage = callback_garage;
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        GarageAPI garageAPI = retrofit.create(GarageAPI.class);
        Call<Garageinfo> call = garageAPI.loadGarage();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Garageinfo> call, Response<Garageinfo> response) {
        if (response.isSuccessful()) {
            Garageinfo garageInfo = response.body();
            System.out.println(garageInfo.getName());
            if (callback_garage != null) {
                callback_garage.garage(garageInfo);
            }
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<Garageinfo> call, Throwable t) {
        t.printStackTrace();
    }

    public interface Callback_Garage {
        void garage(Garageinfo garageInfo);
    }
}
