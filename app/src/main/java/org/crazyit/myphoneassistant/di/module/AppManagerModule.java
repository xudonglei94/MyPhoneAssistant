package org.crazyit.myphoneassistant.di.module;

import android.app.Application;

import org.crazyit.myphoneassistant.data.AppManagerModel;
import org.crazyit.myphoneassistant.presenter.contract.AppInfoContract;
import org.crazyit.myphoneassistant.presenter.contract.AppManagerContract;

import dagger.Module;
import dagger.Provides;
import zlc.season.rxdownload2.RxDownload;

/**
 * Created by Administrator on 2018/6/20.
 */

@Module()
public class AppManagerModule {



    private AppManagerContract.AppManagerView mView;

    public AppManagerModule(AppManagerContract.AppManagerView  view){


        this.mView = view;
    }




    @Provides
    public AppManagerContract.AppManagerView  provideView(){

        return mView;
    }



    @Provides
    public AppManagerContract.IAppManagerModel provideModel(Application application, RxDownload rxDownload){

        return  new AppManagerModel(application,rxDownload);
    }










}











