package org.crazyit.myphoneassistant.presenter;

import android.Manifest;
import android.app.Activity;

import com.tbruyelle.rxpermissions.RxPermissions;

import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.bean.IndexBean;
import org.crazyit.myphoneassistant.bean.PageBean;
import org.crazyit.myphoneassistant.common.rx.RxHttpResponseCompat;
import org.crazyit.myphoneassistant.common.rx.subscriber.ProgressSubcriber;
import org.crazyit.myphoneassistant.data.RecommendModel;
import org.crazyit.myphoneassistant.presenter.contract.RecommendContract;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2018/6/9.
 */
//负责与Model进行交互,要和View进行交互
    //Presenter是通过viewinterface和view来进行交互的
public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View>{
//    //首先它要引用View
//    //显示数据的人
//    private  RecommendContract.View mView;
//
//    //通过这个Model来拿到数据
//    //真正干活的人
//    private RecommendModel mModel;

//    private RxErrorHandler mRxErrorHandler;

    @Inject
    public RecommendPresenter(RecommendModel recommendModel, RecommendContract.View view/*, RxErrorHandler errorHandler*/) {
        super(recommendModel, view);
//        this.mRxErrorHandler=errorHandler;
    }

//    public  void requestPermission(){
//        RxPermissions rxPermissions=new RxPermissions((Activity) mContext);
//        rxPermissions.request(Manifest.permission.READ_PHONE_STATE).subscribe(new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                if (aBoolean){
//                     mView.onRequestPermissonSuccess();
//
//                }else{
//                    mView.onRequestPermissonError();
//                }
//            }
//        });
//    }

//    public  RecommendPresenter(RecommendContract.View view, RecommendModel model){
//        this.mView=view;
//        mModel=model;
//
//
//    }


//    public void requestDatas() {
//
//
////        Activity activity= null;
////        if (mView instanceof Fragment){
////            activity=((Fragment)mView).getActivity();
////        }else {
////            activity=(Activity)mView;
////        }
//
//        //这里需要我们手动去切换成异步操作
//        mModel.getApps()
////                //这行代码的意思是我把getApps这个操作放到子线程中去做
////                .subscribeOn(Schedulers.io())
////                //这个操作是切换线程,切换到主线程中去做
////                .observeOn(AndroidSchedulers.mainThread())
//                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
//                .subscribe(new ProgressDialogSubscriber<PageBean<AppInfo>>(/*mView,mRxErrorHandler*/mContext) {
//                               @Override
//                               public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                                   mView.showResult(appInfoPageBean.getDatas());
//
//                               }
//
////                               @Override
////                               protected boolean isShowDialog() {
////                                   return true;
////                               }
//                           }
////                        new Subscriber<PageBean<AppInfo>>() {
////            //在订阅之前我们需要去做的一些事情
////            @Override
////            public void onStart() {
////                mView.showLoading();
////            }
////
////            @Override
////            public void onCompleted() {
////                mView.dimissLoading();
////
////            }
////
////            @Override
////            public void onError(Throwable e) {
////                mView.dimissLoading();
////                //handle error
////
////            }
////
////            //是我们处理数据,如果onNext这个方法是没有问题的话我们会执行到onCompleted方法中
////            //如果是出错的话我们会出现到onError
////            @Override
////            public void onNext(PageBean<AppInfo> response) {
////                if (response!=null){
////                    //这里注意只做逻辑处理
////                    mView.showResult(response.getDatas());
////                }else {
////                    mView.showNodata();
////                }
////
////
////            }
////        }
//
//
//
//
//
//                );

//使用rxjava之前的写法
//        mView.showLoading();
//
//        mModel.getApps(new Callback<PageBean<AppInfo>>() {
//            @Override
//            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
//                if (response!=null){
//                    //这里注意只做逻辑处理
//                    mView.showResult(response.body().getDatas());
//                }else {
//                    mView.showNodata();
//                }
//                mView.dimissLoading();
//            }
//
//            @Override
//            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
//                mView.dimissLoading();
//                mView.showError(t.getMessage());
//
//            }
//        });

public void requestDatas() {
//        //采用rxjava的方式来请求权限
//
//
//    //先判断它是否授权如果授权成功了那么再去调用这个方法
//    RxPermissions rxPermissions = new RxPermissions((Activity) mContext);
//
//    rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
//            // 将这个Boolean值转换成这个Observable<PageBean<AppInfo>>>对象
//            //相当于将request返回的结果值Boolean装换成了Observable<PageBean<AppInfo>>>对象
//            .flatMap(new Func1<Boolean, Observable<PageBean<AppInfo>>>() {
//                @Override
//                public Observable<PageBean<AppInfo>> call(Boolean aBoolean) {
//
//                    if (aBoolean) {
//
//                        return mModel.getApps().compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult());
//                    } else {
//
//                        return Observable.empty();
//                    }
//
//
//                }
//            })
//            .subscribe(new ProgressSubcriber<PageBean<AppInfo>>(mContext, mView) {
//                @Override
//                public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                    mView.showResult(appInfoPageBean.getDatas());
//                }
//            });

    mModel.index().compose(RxHttpResponseCompat.<IndexBean>compatResult())
            .subscribe(new ProgressSubcriber<IndexBean>(mContext,mView) {
                @Override
                public void onNext(IndexBean indexBean) {
                    mView.showResult(indexBean);
                }
            });
}

    }

