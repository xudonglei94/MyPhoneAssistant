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

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by Administrator on 2018/6/14.
 */

public class AppInfoPresenter extends  BasePresenter<AppInfoModel,AppInfoContract.AppInfoView> {

    public static  final  int TOP_LIST=1;
    public static  final  int GAME=2;
    public static  final  int CATEGORY=3;

    public  static final  int FEATURED=0;
    public  static final  int TOPLIST=1;
    public  static final  int NEWLIST=2;


    @Inject
    public AppInfoPresenter(AppInfoModel appInfoModel, AppInfoContract.AppInfoView topListView) {
        super(appInfoModel, topListView);
    }

    public void  request(int type,int page,int categoryId,int flagType){
        Observer subscriber=null;
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
                public void onComplete() {
                    mView.onLoadMoreComplete();

                }

                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(PageBean<AppInfo> PageBean) {
                    mView.showResult(PageBean);
                }
            };
        }

        Observable observable=getObservable(type,page,categoryId,flagType);
        observable.compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);
    }

    public void  requestData(int type,int page){

        request(type,page,0,0);

    }
    public  void requestCategoryApps(int categoryId,int page,int flagType){
        request(CATEGORY,page,categoryId,flagType);




    }

    private Observable<BaseBean<PageBean<AppInfo>>> getObservable(int type, int page, int categoryId, int flagType){
        switch (type){
            case TOP_LIST:
                return  mModel.topList(page);

            case GAME:
                return  mModel.games(page);

            case CATEGORY:
               if (flagType==FEATURED){
                   return mModel.getFeaturedAppsByCategory(categoryId,page);
               }else if (flagType==TOPLIST){
                   return mModel.getTopListAppsByCategory(categoryId,page);

               }else if (flagType==NEWLIST){

                   return mModel.getNewListAppsByCategory(categoryId,page);
               }

                default:
                    return Observable.empty();
        }

    }
}
