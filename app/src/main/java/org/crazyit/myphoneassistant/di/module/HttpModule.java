package org.crazyit.myphoneassistant.di.module;

import android.app.Application;

import com.google.gson.Gson;

import org.crazyit.myphoneassistant.BuildConfig;
import org.crazyit.myphoneassistant.common.http.CommonParamsInterceptor;
import org.crazyit.myphoneassistant.common.rx.RxErrorHandler;
import org.crazyit.myphoneassistant.data.http.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/6/10.
 */
//@Module
//public class HttpModule {
//
////    @Provides
////    @Singleton
////    public OkHttpClient provideOkHttpClient(Application application,Gson gson){
////        //log拦截器
////        HttpLoggingInterceptor logging=new HttpLoggingInterceptor();
////        //开发模式记录整个body,否则只记录基本信息如返回200,http协议版本等等.
////        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
////        //如果使用HTTPS,需要创建SSLSocketFactory,并设置到client
////        //SSLSocketFactory sslSocketFactory=null;
////        return new OkHttpClient.Builder()
////                //HeadInterceptor实现了Interceptor,用来向Request Header添加一些业务相关的数据,
////                //如App版本,token信息
////                 .addInterceptor(logging)
////                .addInterceptor(new CommonParamsInterceptor(application,gson))
////                //连接超时时间设置
////                .connectTimeout(10, TimeUnit.SECONDS)
////                //读取超时时间设置
////                .readTimeout(10,TimeUnit.SECONDS)
////                .build();
////    }
//@Provides
//@Singleton
//public OkHttpClient provideOkHttpClient(Application application, Gson gson){
//
//
//
//    OkHttpClient.Builder builder = new OkHttpClient.Builder();
//
//
//    //不去掉的话会导致内存溢出OOM,会将这些东西读到内存里面.
////    if(BuildConfig.DEBUG){
////        // log用拦截器
////        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
////
////        // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
////        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
////
////        builder.addInterceptor(logging);
////
////    }
//
//
//
//
//
//    // 如果使用到HTTPS，我们需要创建SSLSocketFactory，并设置到client
////        SSLSocketFactory sslSocketFactory = null;
//
//    return builder
//            .addInterceptor(new CommonParamsInterceptor(application,gson))
//
//            // 连接超时时间设置
//            .connectTimeout(10, TimeUnit.SECONDS)
//            // 读取超时时间设置
//            .readTimeout(10, TimeUnit.SECONDS)
//
//            .build();
//
//
//}
//    @Provides
//    @Singleton
//    public Retrofit provideRetrofit(OkHttpClient okHttpClient){
//
//
//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl(ApiService.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(okHttpClient);
//
//
//        return builder.build();
//
//    }
//
//    //将之前的ApiService提供一个方法在这个HttpModule中创建出来
//    @Provides
//    @Singleton
//    public  ApiService provideApiService(Retrofit retrofit){
//        return  retrofit.create(ApiService.class);
//    }
//    @Provides
//    @Singleton
//    public RxErrorHandler provideErrorHandler(Application application){
//        return  new RxErrorHandler(application);
//    }
//}
@Module
public class HttpModule {





    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Application application, Gson gson){



        OkHttpClient.Builder builder = new OkHttpClient.Builder();


//        if(BuildConfig.DEBUG){
//            // log用拦截器
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//
//            // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//            builder.addInterceptor(logging);
//
//        }





        // 如果使用到HTTPS，我们需要创建SSLSocketFactory，并设置到client
//        SSLSocketFactory sslSocketFactory = null;

        return builder
                .addInterceptor(new CommonParamsInterceptor(application,gson))

                // 连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)

                .build();


    }



    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient);


        return builder.build();

    }



    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit){

        return  retrofit.create(ApiService.class);
    }


    @Provides
    @Singleton
    public RxErrorHandler provideErrorHandlder(Application application){

        return  new RxErrorHandler(application);
    }



}
