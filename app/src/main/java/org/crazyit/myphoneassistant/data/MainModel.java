package org.crazyit.myphoneassistant.data;

import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.bean.BaseBean;
import org.crazyit.myphoneassistant.bean.requestbean.AppsUpdateBean;
import org.crazyit.myphoneassistant.data.http.ApiService;
import org.crazyit.myphoneassistant.presenter.contract.MainContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/6/28.
 */

public class MainModel implements MainContract.IMainModel {

    private ApiService mApiService;

    public MainModel(ApiService apiService){
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<List<AppInfo>>> getUpdateApps(AppsUpdateBean param) {
        return mApiService.getAppsUpdateinfo(param.getPackageName(),param.getVersionCode());
    }
}
