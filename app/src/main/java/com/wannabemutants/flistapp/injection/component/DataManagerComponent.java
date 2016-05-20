package com.wannabemutants.flistapp.injection.component;

import com.wannabemutants.flistapp.data.DataManager;
import com.wannabemutants.flistapp.injection.module.DataManagerModule;
import com.wannabemutants.flistapp.injection.scope.PerDataManager;

import dagger.Component;

/**
 * Created by cheglader on 5/14/16.
 */
@PerDataManager
@Component(dependencies = ApplicationComponent.class, modules = DataManagerModule.class)
public interface DataManagerComponent {

    void inject(DataManager dataManager);
}