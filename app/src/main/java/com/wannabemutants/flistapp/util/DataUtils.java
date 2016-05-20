package com.wannabemutants.flistapp.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by cheglader on 5/15/16.
 */
public class DataUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
