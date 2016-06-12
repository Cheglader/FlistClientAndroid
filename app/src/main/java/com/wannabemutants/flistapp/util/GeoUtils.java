package com.wannabemutants.flistapp.util;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.wannabemutants.flistapp.Manifest;

/**
 * Created by cheglader on 6/12/16.
 */
public class GeoUtils {
    static GoogleApiClient mGoogleApiClient;

    static Location getCurrentLocation(Activity context) {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(context)
                    .addOnConnectionFailedListener(context)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }
        if(ContextCompat.checkSelfPermission(context,Manifest.permission.ACCESS_COARSE_LOCATION)) {
            return LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }
        return null;
    }
}
