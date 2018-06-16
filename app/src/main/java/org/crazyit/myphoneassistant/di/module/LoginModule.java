package org.crazyit.myphoneassistant.di.module;

import org.crazyit.myphoneassistant.data.AppInfoModel;
import org.crazyit.myphoneassistant.data.LoginModel;
import org.crazyit.myphoneassistant.data.http.ApiService;
import org.crazyit.myphoneassistant.presenter.contract.AppInfoContract;
import org.crazyit.myphoneassistant.presenter.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/10.
 */
@Module
public class LoginModule {

    private LoginContract.LoginView mView;

    public LoginModule(LoginContract.LoginView view){
        this.mView = view;
    }

    @Provides
    public LoginContract.LoginView provideView(){

        return mView;
    }

    @Provides
    public LoginContract.ILoginModel privodeModel(ApiService apiService){
        return  new LoginModel(apiService);
    }












}
