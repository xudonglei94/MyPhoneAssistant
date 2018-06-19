package org.crazyit.myphoneassistant.presenter.contract;

import org.crazyit.myphoneassistant.bean.BaseBean;
import org.crazyit.myphoneassistant.bean.LoginBean;
import org.crazyit.myphoneassistant.ui.BaseView;

import io.reactivex.Observable;


/**
 * Created by Administrator on 2018/6/15.
 */

public interface LoginContract {
    public interface  ILoginModel{
//        void login(String phone,String pwd);



        Observable<BaseBean<LoginBean>> login(String phone, String pwd);




    }


    public interface  LoginView extends BaseView {


        void checkPhoneError();
        void checkPhoneSuccess();

        void loginSuccess(LoginBean bean);
//        void loginError(String msg);

    }

}
