package org.crazyit.myphoneassistant.data;

import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.bean.BaseBean;
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

    public Observable<BaseBean<PageBean<AppInfo>>> getApps(/*Callback<PageBean<AppInfo>> callback*/){
//        HttpManager manager=new HttpManager();
//        ApiService apiService=manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);

//        mApiServcie.getApps("{'page':0}").enqueue(callback);
//        return  mApiServcie.getApps("{'page':0}");
        return  mApiService.getApps("{'page':0}");

    }
}
