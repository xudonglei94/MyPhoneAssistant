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

public class RecommendModel {

    private ApiService mApiService;

    public RecommendModel(ApiService mApiServcie) {
        this.mApiService = mApiServcie;
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getApps(){

        return  mApiService.getApps("{'page':0}");

    }
    public Observable<BaseBean<IndexBean>> index(){
        return  mApiService.index();

    }
}
