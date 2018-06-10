package org.crazyit.myphoneassistant.ui.data;

import org.crazyit.myphoneassistant.ui.bean.AppInfo;
import org.crazyit.myphoneassistant.ui.bean.PageBean;
import org.crazyit.myphoneassistant.ui.http.ApiService;
import org.crazyit.myphoneassistant.ui.http.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
