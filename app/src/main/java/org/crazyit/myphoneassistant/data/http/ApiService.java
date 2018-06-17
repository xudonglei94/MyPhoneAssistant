package org.crazyit.myphoneassistant.data.http;

import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.bean.BaseBean;
import org.crazyit.myphoneassistant.bean.Category;
import org.crazyit.myphoneassistant.bean.IndexBean;
import org.crazyit.myphoneassistant.bean.LoginBean;
import org.crazyit.myphoneassistant.bean.PageBean;
import org.crazyit.myphoneassistant.bean.requestbean.LoginRequestBean;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/6/8.
 */

public interface ApiService {


    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";




//    //没有用到rxjava的时候我们是这样写
//    @GET("featured")
//    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);


    //用到rxjava的情况下的时候
//    @GET("featured2")
//    public Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);
    @GET("featured2")
    public Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);

    @GET("index")
    public Observable<BaseBean<IndexBean>> index();
    @GET("toplist")
    public Observable<BaseBean<PageBean<AppInfo>>> topList(@Query("page") int page);//{"page":0}

    @GET("game")
    public  Observable<BaseBean<PageBean<AppInfo>>> games(@Query("page") int page);

    @POST("login")
    Observable<BaseBean<LoginBean>> login(@Body LoginRequestBean param);

    @GET("category")
    Observable<BaseBean<List<Category>>> getCategories();


    @GET("category/featured/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory(@Path("categoryid") int categoryid, @Query("page") int page);

    @GET("category/toplist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory(@Path("categoryid") int categoryid,@Query("page") int page);

    @GET("category/newlist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory(@Path("categoryid") int categoryid,@Query("page") int page);




//    //{"phone":"","password":""}
//    @POST("login")
//    public  Observable<BaseBean> login(@Body LoginRequestBean bean );
//
//    @FormUrlEncoded // FormBody
//    @POST("login")
//    public   void login2(@Field("phone") String phone);


}
