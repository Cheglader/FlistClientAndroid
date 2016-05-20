package com.wannabemutants.flistapp.injection.component;

import android.app.Application;

import com.wannabemutants.flistapp.data.DataManager;
import com.wannabemutants.flistapp.injection.module.ApplicationModule;
import com.wannabemutants.flistapp.view.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cheglader on 5/14/16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);

    Application application();
    DataManager dataManager();
}