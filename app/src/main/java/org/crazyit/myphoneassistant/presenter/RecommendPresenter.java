package org.crazyit.myphoneassistant.presenter;

import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.bean.PageBean;
import org.crazyit.myphoneassistant.data.RecommendModel;
import org.crazyit.myphoneassistant.presenter.contract.RecommendContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/6/9.
 */
//负责与Model进行交互,要和View进行交互
    //Presenter是通过viewinterface和view来进行交互的
public class RecommendPresenter implements RecommendContract.Presenter {
    //首先它要引用View
    //显示数据的人
    private  RecommendContract.View mView;

    //通过这个Model来拿到数据
    //真正干活的人
    private RecommendModel mModel;

    public  RecommendPresenter(RecommendContract.View view, RecommendModel model){
        this.mView=view;
        mModel=model;


    }

    @Override
    public void requestDatas() {


        mView.showLoading();

        mModel.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                if (response!=null){
                    //这里注意只做逻辑处理
                    mView.showResult(response.body().getDatas());
                }else {
                    mView.showNodata();
                }
                mView.dimissLoading();
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
                mView.dimissLoading();
                mView.showError(t.getMessage());

            }
        });

    }
}
