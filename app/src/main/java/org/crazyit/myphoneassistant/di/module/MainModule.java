package org.crazyit.myphoneassistant.di.module;

import org.crazyit.myphoneassistant.data.MainModel;
import org.crazyit.myphoneassistant.data.http.ApiService;
import org.crazyit.myphoneassistant.di.FragmentScope;
import org.crazyit.myphoneassistant.presenter.contract.MainContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/28.
 */

@Module
public class MainModule {


    private MainContract.MainView mView;


    public MainModule(MainContract.MainView view){

        this.mView = view;
    }



    @FragmentScope
    @Provides
    public MainContract.MainView provideView(){


        return  mView;
    }


    @FragmentScope
    @Provides
    public MainContract.IMainModel provideModel(ApiService apiService){

        return  new MainModel(apiService);
    }


}
