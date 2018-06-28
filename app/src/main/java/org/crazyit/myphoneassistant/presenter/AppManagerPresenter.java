package org.crazyit.myphoneassistant.presenter;

import android.content.IntentFilter;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.common.Constant;
import org.crazyit.myphoneassistant.common.apkparset.AndroidApk;
import org.crazyit.myphoneassistant.common.rx.RxSchedulers;
import org.crazyit.myphoneassistant.common.rx.subscriber.ProgressSubcriber;
import org.crazyit.myphoneassistant.common.util.ACache;
import org.crazyit.myphoneassistant.presenter.contract.AppManagerContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by Administrator on 2018/6/20.
 */

public class AppManagerPresenter extends BasePresenter<AppManagerContract.IAppManagerModel,AppManagerContract.AppManagerView> {

    @Inject
    public AppManagerPresenter(AppManagerContract.IAppManagerModel iAppManagerModel, AppManagerContract.AppManagerView appManagerView) {
        super(iAppManagerModel, appManagerView);
    }



    public void getDownlodingApps(){


        mModel.getDownloadRecord().compose(RxSchedulers.<List<DownloadRecord>>io_main())


                .subscribe(new ProgressSubcriber<List<DownloadRecord>>(mContext,mView) {
                    @Override
                    public void onNext(List<DownloadRecord> downloadRecords) {

                        mView.showDownloading(downloadRecordFilter(downloadRecords));


                    }
                });


    }



    public void getLocalApks(){


        mModel.getLocalApks().compose(RxSchedulers.<List<AndroidApk>>io_main())
                .subscribe(new ProgressSubcriber<List<AndroidApk>>(mContext,mView) {
                    @Override
                    public void onNext(List<AndroidApk> androidApks) {

                        mView.showApps(androidApks);
                    }
                });
    }



    public void getInstalledApps(){



        mModel.getInstalledApps().compose(RxSchedulers.<List<AndroidApk>>io_main())
                .subscribe(new ProgressSubcriber<List<AndroidApk>>(mContext,mView) {
                    @Override
                    public void onNext(List<AndroidApk> androidApks) {
                        mView.showApps(androidApks);
                    }
                });
    }
    public void getUpdateApps(){
        String json= ACache.get(mContext).getAsString(Constant.APP_UPDATE_LIST);
        if(!TextUtils.isEmpty(json)){

            Gson gson = new Gson();
            List<AppInfo> apps = gson.fromJson(json,new TypeToken<List<AppInfo>>(){}.getType());


            Observable.just(apps)
                    .compose(RxSchedulers.<List<AppInfo>>io_main())

                    .subscribe(new ProgressSubcriber<List<AppInfo>>(mContext,mView) {
                        @Override
                        public void onNext(List<AppInfo> appInfos) {

                            mView.showUpdateApps(appInfos);
                        }
                    });


        }

    }


    public RxDownload getRxDowanload(){

        return mModel.getRxDownload();
    }



    private List<DownloadRecord> downloadRecordFilter(List<DownloadRecord> downloadRecords){

        List<DownloadRecord> newList = new ArrayList<>();
        for (DownloadRecord r :downloadRecords){

            if(r.getFlag() != DownloadFlag.COMPLETED){

                newList.add(r);
            }
        }

        return newList;

    }
}
