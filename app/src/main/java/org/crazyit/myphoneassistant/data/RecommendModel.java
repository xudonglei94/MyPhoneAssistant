package org.crazyit.myphoneassistant.data;

import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.bean.PageBean;
import org.crazyit.myphoneassistant.data.http.ApiService;


import retrofit2.Callback;

/**
 * Created by Administrator on 2018/6/9.
 */

public class RecommendModel {

    private ApiService mApiServcie;

    public RecommendModel(ApiService mApiServcie) {
        this.mApiServcie = mApiServcie;
    }

    public void   getApps(Callback<PageBean<AppInfo>> callback){
//        HttpManager manager=new HttpManager();
//        ApiService apiService=manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
        mApiServcie.getApps("{'page':0}").enqueue(callback);

    }
}
