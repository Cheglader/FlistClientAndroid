package com.wannabemutants.flistapp;

import android.app.Application;
import android.content.Context;

import com.wannabemutants.flistapp.injection.component.ApplicationComponent;
import com.wannabemutants.flistapp.injection.component.DaggerApplicationComponent;
import com.wannabemutants.flistapp.injection.module.ApplicationModule;

import timber.log.Timber;

/**
 * Created by cheglader on 5/14/16.
 */
public class FlistAppApplication extends Application {
    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static FlistAppApplication get(Context context) {
        return (FlistAppApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent () { return mApplicationComponent; }

    public void setComponent (ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
