package org.crazyit.myphoneassistant.data;

import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.bean.BaseBean;
import org.crazyit.myphoneassistant.bean.IndexBean;
import org.crazyit.myphoneassistant.bean.PageBean;
import org.crazyit.myphoneassistant.data.http.ApiService;


import io.reactivex.Observable;


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

    public Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory( int categoryid,  int page){

        return  mApiService.getFeaturedAppsByCategory(categoryid,page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory( int categoryid, int page){

        return  mApiService.getTopListAppsByCategory(categoryid,page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory( int categoryid, int page){

        return  mApiService.getNewListAppsByCategory(categoryid,page);
    }
    public  Observable<BaseBean<AppInfo>> getAppDetail(int id){
        return  mApiService.getAppDetail(id);
    }
    public Observable<BaseBean<PageBean<AppInfo>>> getHotApps(int page){

        return  mApiService.getHotApps(page);
    }
}
