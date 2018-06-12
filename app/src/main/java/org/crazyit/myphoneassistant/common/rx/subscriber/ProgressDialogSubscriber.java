package org.crazyit.myphoneassistant.common.rx.subscriber;


import android.content.Context;


import org.crazyit.myphoneassistant.common.util.ProgressDialogHandler;


/**
 * Created by Administrator on 2018/6/12.
 */
//因为这个地方我们没有实现onNext这个方法所以我们将这个类定义为了abstract抽象类.
public abstract class ProgressDialogSubscriber<T> extends  ErrorHandlerSubscriber<T> implements ProgressDialogHandler.OnProgressCancelListener {


    ////
    private  ProgressDialogHandler mProgressDialogHandler;

    /////
    public ProgressDialogSubscriber(Context context){
        super(context);
        mProgressDialogHandler=new ProgressDialogHandler(mContext,true,this);
    }
    protected  boolean isShowProgressDialog(){
        return  true;
    }

    @Override
    public void onCancelProgress() {
        unsubscribe();
    }

//    private Context mContext;
//    private BaseView baseView;

//    private  ProgressDialog progressDialog;

//    public ProgressDialogSubscriber(BaseView view, RxErrorHandler rxErrorHandler) {
//        super(rxErrorHandler);
//        this.baseView=view;
//    }

    @Override
    public void onStart() {
        if (isShowProgressDialog()){
            this.mProgressDialogHandler.showProgressDialog();
        }

    }

    @Override
    public void onCompleted() {
        if (isShowProgressDialog()){
            this.mProgressDialogHandler.dismissProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (isShowProgressDialog()) {
            this.mProgressDialogHandler.dismissProgressDialog();
        }

    }

//    private  void initProgressDialog(){
//        if (progressDialog==null){
//            //通过构造方法来获取到这个context
//            progressDialog=new ProgressDialog(mContext);
//            progressDialog.setMessage("loading ............");
//        }
//    }

//    private  void showProgressDialog(){
////        initProgressDialog();
////        progressDialog.show();
//        baseView.showLoading();
//
//    }
//    private void dismissProgressDialog(){
//
////        if (progressDialog!=null&&progressDialog.isShowing()){
////            progressDialog.dismiss();
////        }
//        baseView.dimissLoading();
//    }
}
