package org.crazyit.myphoneassistant.di.component;

import android.app.Application;

import org.crazyit.myphoneassistant.common.rx.RxErrorHandler;
import org.crazyit.myphoneassistant.data.http.ApiService;
import org.crazyit.myphoneassistant.di.module.AppModule;
import org.crazyit.myphoneassistant.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/10.
 */
//因为AppModule它加上了Singleton,所以说这里必须要加上singleton否则是会报错的
@Singleton
@Component(modules= {AppModule.class, HttpModule.class})
public interface AppComponent {

    public ApiService getApiService();
    public Application getApplication();
    public RxErrorHandler getRxErrorHandler();

}
