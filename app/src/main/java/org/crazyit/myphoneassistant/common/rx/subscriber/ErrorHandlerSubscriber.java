package org.crazyit.myphoneassistant.common.rx.subscriber;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.crazyit.myphoneassistant.common.exception.BaseException;
import org.crazyit.myphoneassistant.common.rx.RxErrorHandler;
import org.crazyit.myphoneassistant.ui.activity.LoginActivity;

/**
 * Created by Administrator on 2018/6/11.
 */

public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscriber<T> {


    protected RxErrorHandler mRxErrorHandler=null;

    protected Context mContext;
    public ErrorHandlerSubscriber(Context context){
        this.mContext=context;

        mRxErrorHandler=new RxErrorHandler(mContext);
    }

//    public  ErrorHandlerSubscriber(RxErrorHandler errorHandler){
//        this.mRxErrorHandler=errorHandler;
//    }
    @Override
    public void onError(Throwable e) {

//        e.printStackTrace();
        BaseException baseException =  mRxErrorHandler.handleError(e);
//        mRxErrorHandler.showErrorMessage(baseException);
        if(baseException==null){
            e.printStackTrace();
            Log.d("ErrorHandlerSubscriber",e.getMessage());
        }
        else {
            mRxErrorHandler.showErrorMessage(baseException);

            if (baseException.getCode()==BaseException.ERROR_TOKEN){
                toLogin();
            }

        }

    }

    private void toLogin() {
        Intent intent=new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }

}
