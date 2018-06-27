package org.crazyit.myphoneassistant.presenter.contract;

import org.crazyit.myphoneassistant.common.apkparset.AndroidApk;
import org.crazyit.myphoneassistant.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by Administrator on 2018/6/20.
 */

public interface AppManagerContract {




    interface AppManagerView extends BaseView{


        void showDownloading(List<DownloadRecord> downloadRecords);
        void showApps(List<AndroidApk> apps);



    }

    interface IAppManagerModel{



        Observable<List<DownloadRecord>> getDownloadRecord();

        RxDownload getRxDownload();

        Observable<List<AndroidApk>> getLocalApks();
        Observable<List<AndroidApk>> getInstalledApps();




    }



}
