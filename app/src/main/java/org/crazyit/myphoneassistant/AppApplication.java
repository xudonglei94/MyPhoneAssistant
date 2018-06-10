package org.crazyit.myphoneassistant;

import android.app.Application;
import android.content.Context;

import org.crazyit.myphoneassistant.di.component.AppComponent;

import org.crazyit.myphoneassistant.di.component.DaggerAppComponent;
import org.crazyit.myphoneassistant.di.module.AppModule;
import org.crazyit.myphoneassistant.di.module.HttpModule;

/**
 * Created by Administrator on 2018/6/10.
 */

public class AppApplication extends Application{
    //单例的东西我们都放在这里,有个dragger2之后就不需要这样做了可以实例化管理我们这个类可以对生命周期进行管理



    private AppComponent mAppComponent;

    //提供Application对象
    public  static AppApplication get(Context context){
        return (AppApplication) context.getApplicationContext();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
    @Override
    public void onCreate() {
        super.onCreate();

       mAppComponent= DaggerAppComponent.builder().appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build();
    }
}
