package org.crazyit.myphoneassistant.di.module;

import android.app.ProgressDialog;

import org.crazyit.myphoneassistant.data.AppInfoModel;
import org.crazyit.myphoneassistant.data.http.ApiService;
import org.crazyit.myphoneassistant.presenter.contract.AppInfoContract;
import org.crazyit.myphoneassistant.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/10.
 */
//这里面的工作全部都是用来new东西的
//这个Module是告诉dragger2这个类是用来提供实例的
@Module
public class TopListModule {

    //通过构造方法把view传进来
    private  AppInfoContract.TopListView mView;

    public TopListModule(AppInfoContract.TopListView view){
        this.mView=view;
    }

    //这个方法以此来告诉dragger2是来提供实例的
    //Provide的作用是告诉dragger2这个方法是来提供实例的
    //这里其实是两种方法来初始化这个Present
//    @Provides
//    public AppInfoContract.Presenter providePresenter(AppInfoContract.View view,AppInfoModel model){
//
//        //这个view是通过构造方式将它传递进来的
//        return  new RecommendPresenter(view,model);
//
//    }
    @Provides
    public  AppInfoContract.TopListView provideView(){
        return  mView;
    }
    @Provides
    public AppInfoModel provideModel(ApiService apiService){
        return new AppInfoModel(apiService);
    }



}
