package org.crazyit.myphoneassistant.di.module;

import org.crazyit.myphoneassistant.data.AppInfoModel;
import org.crazyit.myphoneassistant.data.http.ApiService;
import org.crazyit.myphoneassistant.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/10.
 */

@Module(includes = {AppModelModule.class})
public class AppDetailModule {


    private AppInfoContract.AppDetailView mView;

    public AppDetailModule(AppInfoContract.AppDetailView view){
        this.mView=view;
    }

    @Provides
    public  AppInfoContract.AppDetailView provideView(){
        return  mView;
    }

}
