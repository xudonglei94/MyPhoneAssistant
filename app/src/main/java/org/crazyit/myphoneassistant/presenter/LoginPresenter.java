package org.crazyit.myphoneassistant.presenter;

import android.util.Log;

import com.hwangjr.rxbus.RxBus;

import org.crazyit.myphoneassistant.bean.LoginBean;
import org.crazyit.myphoneassistant.common.Constant;
import org.crazyit.myphoneassistant.common.rx.RxHttpResponseCompat;
import org.crazyit.myphoneassistant.common.rx.subscriber.ErrorHandlerSubscriber;
import org.crazyit.myphoneassistant.common.util.ACache;
import org.crazyit.myphoneassistant.common.util.VerificationUtils;
import org.crazyit.myphoneassistant.presenter.contract.LoginContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/15.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel,LoginContract.LoginView> {



    @Inject
    public LoginPresenter(LoginContract.ILoginModel iLoginModel, LoginContract.LoginView loginView) {
        super(iLoginModel, loginView);
    }



    public void login(String phone,String pwd){


        Log.d("LoginPresenter","phone="+phone);
        Log.d("LoginPresenter","pwd="+pwd);

        if(!VerificationUtils.matcherPhoneNum(phone)){

            mView.checkPhoneError();
            return;
        }else {
            mView.checkPhoneSuccess();
        }


        mModel.login(phone,pwd).compose(RxHttpResponseCompat.<LoginBean>compatResult())
                .subscribe(new ErrorHandlerSubscriber<LoginBean>(mContext) {
                    @Override
                    public void onStart() {

                        mView.showLoading();
                    }

                    @Override
                    public void onCompleted() {

                        mView.dismissLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.dismissLoading();
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        mView.loginSuccess(loginBean);
                        saveUser(loginBean);

                        RxBus.get().post(loginBean.getUser());
                    }
                });



    }


    //保存用户信息
    private void saveUser(LoginBean bean){

        ACache aCache = ACache.get(mContext);

        aCache.put(Constant.TOKEN,bean.getToken());
        aCache.put(Constant.USER,bean.getUser());
    }

}
