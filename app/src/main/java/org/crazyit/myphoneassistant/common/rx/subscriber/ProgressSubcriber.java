package org.crazyit.myphoneassistant.common.rx.subscriber;

import android.content.Context;

import org.crazyit.myphoneassistant.common.exception.BaseException;
import org.crazyit.myphoneassistant.ui.BaseView;

/**
 * Created by Administrator on 2018/6/12.
 */

public  abstract  class ProgressSubcriber<T> extends ErrorHandlerSubscriber<T>  {




    private BaseView mView;


    public ProgressSubcriber(Context context, BaseView view) {
        super(context);
        this.mView = view;

    }



    public boolean isShowProgress(){
        return true;
    }



    @Override
    public void onStart() {

        if(isShowProgress()){
            mView.showLoading();
        }

    }

    @Override
    public void onCompleted() {

        mView.dismissLoading();
    }

    @Override
    public void onError(Throwable e) {
        BaseException baseException =  mRxErrorHandler.handleError(e);
        mView.showError(baseException.getDisplayMessage());

    }

}