package com.wannabemutants.flistapp.data.remote;

import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by cheglader on 5/15/16.
 */
public class RetrofitHelper {

    public FlistService newFlistService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(FlistService.ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .build();
        return restAdapter.create(FlistService.class);
    }

}