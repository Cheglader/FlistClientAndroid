package com.wannabemutants.flistapp.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.wannabemutants.flistapp.util.AccountType;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent;
        super.onCreate(savedInstanceState);

        if(isSignedIn()) {
            intent = new Intent(this, MainActivity.class);
        } else {
            intent = new Intent(this, SignInActivity.class);
        }
        startActivity(intent);
        finish();
    }

    private boolean isSignedIn() {
        boolean signed_in = false;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        AccountType actype = AccountType
                             .values()[prefs.getInt("account_type", AccountType.NONE.ordinal())];
        if(actype == AccountType.GOOGLE) {
            signed_in = true;
        }
        // TODO add signin attempt if no account was logged in
        return signed_in;
    }
}
