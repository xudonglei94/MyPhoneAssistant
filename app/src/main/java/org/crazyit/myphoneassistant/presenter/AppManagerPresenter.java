package org.crazyit.myphoneassistant.presenter;

import org.crazyit.myphoneassistant.common.apkparset.AndroidApk;
import org.crazyit.myphoneassistant.common.rx.RxSchedulers;
import org.crazyit.myphoneassistant.common.rx.subscriber.ProgressSubcriber;
import org.crazyit.myphoneassistant.presenter.contract.AppManagerContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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


    public RxDownload geRxDowanload(){

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
