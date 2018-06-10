package org.crazyit.myphoneassistant.di.module;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/10.
 */
@Module
public class AppModule {


    private  Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return mApplication;

    }

    @Provides
    @Singleton
    public Gson provideGson(){
        return new Gson();

    }
}
