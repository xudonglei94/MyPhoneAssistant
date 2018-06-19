package org.crazyit.myphoneassistant.presenter;

import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.bean.BaseBean;
import org.crazyit.myphoneassistant.bean.PageBean;
import org.crazyit.myphoneassistant.common.rx.RxHttpResponseCompat;
import org.crazyit.myphoneassistant.common.rx.subscriber.ErrorHandlerSubscriber;
import org.crazyit.myphoneassistant.common.rx.subscriber.ProgressSubcriber;
import org.crazyit.myphoneassistant.data.AppInfoModel;
import org.crazyit.myphoneassistant.presenter.contract.AppInfoContract;

import javax.inject.Inject;



/**
 * Created by Administrator on 2018/6/14.
 */

public class AppDetailPresenter extends  BasePresenter<AppInfoModel,AppInfoContract.AppDetailView> {




    @Inject
    public AppDetailPresenter(AppInfoModel appInfoModel, AppInfoContract.AppDetailView appDetailView) {
        super(appInfoModel, appDetailView);
    }

    public void getAppDetail(int id){


        mModel.getAppDetail(id).compose(RxHttpResponseCompat.<AppInfo>compatResult())
                .subscribe(new ProgressSubcriber<AppInfo>(mContext,mView) {
                    @Override
                    public void onNext(AppInfo appInfo) {

                        mView.showAppDetail(appInfo);
                    }
                });
    }
}
