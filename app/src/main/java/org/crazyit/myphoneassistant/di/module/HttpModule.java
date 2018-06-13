package org.crazyit.myphoneassistant.di.module;

import android.app.Application;

import org.crazyit.myphoneassistant.common.rx.RxErrorHandler;
import org.crazyit.myphoneassistant.data.http.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/6/10.
 */
@Module
public class HttpModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(){
        //log拦截器
        HttpLoggingInterceptor logging=new HttpLoggingInterceptor();
        //开发模式记录整个body,否则只记录基本信息如返回200,http协议版本等等.
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        //如果使用HTTPS,需要创建SSLSocketFactory,并设置到client
        //SSLSocketFactory sslSocketFactory=null;
        return new OkHttpClient.Builder()
                //HeadInterceptor实现了Interceptor,用来向Request Header添加一些业务相关的数据,
                //如App版本,token信息
                // .addInterceptor(new HeadInterceptor)
                .addInterceptor(logging)
                //连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                //读取超时时间设置
                .readTimeout(10,TimeUnit.SECONDS)
                .build();
    }
    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient);


        return builder.build();

    }

    //将之前的ApiService提供一个方法在这个HttpModule中创建出来
    @Provides
    @Singleton
    public  ApiService provideApiService(Retrofit retrofit){
        return  retrofit.create(ApiService.class);
    }
    @Provides
    @Singleton
    public RxErrorHandler provideErrorHandler(Application application){
        return  new RxErrorHandler(application);
    }
}
