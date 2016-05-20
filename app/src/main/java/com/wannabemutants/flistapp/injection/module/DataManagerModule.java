package com.wannabemutants.flistapp.injection.module;

import com.wannabemutants.flistapp.data.remote.FlistService;
import com.wannabemutants.flistapp.data.remote.RetrofitHelper;
import com.wannabemutants.flistapp.injection.scope.PerDataManager;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by cheglader on 5/14/16.
 */
@Module
public class DataManagerModule {

    public DataManagerModule() {

    }


    @Provides
    @PerDataManager
    FlistService provideHackerNewsService() {
        return new RetrofitHelper().newFlistService();
    }

    @Provides
    @PerDataManager
    Scheduler provideSubscribeScheduler() {
        return Schedulers.io();
    }
}