package org.crazyit.myphoneassistant.data;

import org.crazyit.myphoneassistant.bean.BaseBean;
import org.crazyit.myphoneassistant.bean.LoginBean;
import org.crazyit.myphoneassistant.bean.requestbean.LoginRequestBean;
import org.crazyit.myphoneassistant.data.http.ApiService;
import org.crazyit.myphoneassistant.presenter.contract.LoginContract;

import io.reactivex.Observable;


/**
 * Created by Administrator on 2018/6/15.
 */

public class LoginModel implements LoginContract.ILoginModel {

    private ApiService mApiService;

    public LoginModel(ApiService apiService){
        this.mApiService = apiService;

    }

    @Override
    public Observable<BaseBean<LoginBean>> login(String phone, String pwd) {

        LoginRequestBean param = new LoginRequestBean();
        param.setEmail(phone);
        param.setPassword(pwd);

        return mApiService.login(param);
    }
}
