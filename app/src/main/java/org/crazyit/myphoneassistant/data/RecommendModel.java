package org.crazyit.myphoneassistant.data;

import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.bean.PageBean;
import org.crazyit.myphoneassistant.data.http.ApiService;
import org.crazyit.myphoneassistant.data.http.HttpManager;

import retrofit2.Callback;

/**
 * Created by Administrator on 2018/6/9.
 */

public class RecommendModel {

    public void   getApps(Callback<PageBean<AppInfo>> callback){
        HttpManager manager=new HttpManager();
        ApiService apiService=manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
        apiService.getApps("{'page':0}").enqueue(callback);

    }
}
