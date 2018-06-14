package org.crazyit.myphoneassistant.presenter;

import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.bean.PageBean;
import org.crazyit.myphoneassistant.common.rx.RxHttpResponseCompat;
import org.crazyit.myphoneassistant.common.rx.subscriber.ErrorHandlerSubscriber;
import org.crazyit.myphoneassistant.common.rx.subscriber.ProgressSubcriber;
import org.crazyit.myphoneassistant.data.AppInfoModel;
import org.crazyit.myphoneassistant.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Administrator on 2018/6/14.
 */

public class TopListPresenter extends  BasePresenter<AppInfoModel,AppInfoContract.TopListView> {

    @Inject
    public TopListPresenter(AppInfoModel appInfoModel, AppInfoContract.TopListView topListView) {
        super(appInfoModel, topListView);
    }

    public void  getTopListApps(int page){

        Subscriber subscriber=null;
        //第一页显示一个loading的界面------------
        if (page==0){
            subscriber=new ProgressSubcriber<PageBean<AppInfo>>(mContext,mView) {
                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {

                    mView.showResult(appInfoPageBean);

                }
            };

        }else{
            //加载下一页
            //ErrorHandlerSubscriber的原因是下一页也可能会出错所以方便处理错误
            subscriber=new ErrorHandlerSubscriber<PageBean<AppInfo>>( mContext) {
                @Override
                public void onCompleted() {
                    mView.onLoadMoreComplete();

                }

                @Override
                public void onNext(PageBean<AppInfo> PageBean) {
                    mView.showResult(PageBean);
                }
            };
        }

        mModel.topList(page)
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);
    }
}
