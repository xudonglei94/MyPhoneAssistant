package org.crazyit.myphoneassistant.di.module;

import org.crazyit.myphoneassistant.data.AppInfoModel;
import org.crazyit.myphoneassistant.data.http.ApiService;
import org.crazyit.myphoneassistant.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/10.
 */

@Module
public class AppModelModule {

    @Provides
    public AppInfoModel provideModel(ApiService apiService){
        return new AppInfoModel(apiService);
    }

}
