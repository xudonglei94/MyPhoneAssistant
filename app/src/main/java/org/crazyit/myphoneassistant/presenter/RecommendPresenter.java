package org.crazyit.myphoneassistant.presenter;

import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.bean.PageBean;
import org.crazyit.myphoneassistant.data.RecommendModel;
import org.crazyit.myphoneassistant.presenter.contract.RecommendContract;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.Subscriber;
import rx.schedulers.Schedulers;

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

    @Inject
    public RecommendPresenter(RecommendModel recommendModel, RecommendContract.View view) {
        super(recommendModel, view);
    }

//    public  RecommendPresenter(RecommendContract.View view, RecommendModel model){
//        this.mView=view;
//        mModel=model;
//
//
//    }


    public void requestDatas() {
        //这里需要我们手动去切换成异步操作
        mModel.getApps()
                //这行代码的意思是我把getApps这个操作放到子线程中去做
                .subscribeOn(Schedulers.io())
                //这个操作是切换线程,切换到主线程中去做
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PageBean<AppInfo>>() {
            //在订阅之前我们需要去做的一些事情
            @Override
            public void onStart() {
                mView.showLoading();
            }

            @Override
            public void onCompleted() {
                mView.dimissLoading();

            }

            @Override
            public void onError(Throwable e) {
                mView.dimissLoading();
                //handle error

            }

            //是我们处理数据,如果onNext这个方法是没有问题的话我们会执行到onCompleted方法中
            //如果是出错的话我们会出现到onError
            @Override
            public void onNext(PageBean<AppInfo> response) {
                if (response!=null){
                    //这里注意只做逻辑处理
                    mView.showResult(response.getDatas());
                }else {
                    mView.showNodata();
                }


            }
        });

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

    }
}
