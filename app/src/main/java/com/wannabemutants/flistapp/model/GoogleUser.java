package com.wannabemutants.flistapp.model;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by cheglader on 5/15/16.
 */
public class GoogleUser implements UserInterface {
    private String id;
    private String first_name;
    private String api_key;

    public GoogleUser(String id, String first_name, String api_key) {
        this.id = id;
        this.first_name = first_name;
        this.api_key = api_key;
    }


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getApiKey() {
        return this.api_key;
    }

    @Override
    public String getName() {
        return this.first_name;
    }
}
