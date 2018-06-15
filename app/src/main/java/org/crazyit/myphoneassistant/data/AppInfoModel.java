package org.crazyit.myphoneassistant.data;

import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.bean.BaseBean;
import org.crazyit.myphoneassistant.bean.IndexBean;
import org.crazyit.myphoneassistant.bean.PageBean;
import org.crazyit.myphoneassistant.data.http.ApiService;


import retrofit2.Callback;
import rx.Observable;

/**
 * Created by Administrator on 2018/6/9.
 */

public class AppInfoModel {

    private ApiService mApiService;

    public AppInfoModel(ApiService mApiServcie) {
        this.mApiService = mApiServcie;
    }

//    public Observable<BaseBean<PageBean<AppInfo>>> getApps(){
//
//        return  mApiService.getApps("{'page':0}");
//
//    }
    public Observable<BaseBean<IndexBean>> index(){
        return  mApiService.index();

    }
    public Observable<BaseBean<PageBean<AppInfo>>> topList(int page){
        return mApiService.topList(page);
    }
    public Observable<BaseBean<PageBean<AppInfo>>> games(int page){
        return mApiService.games(page);
    }
}
