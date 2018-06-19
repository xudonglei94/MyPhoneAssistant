package org.crazyit.myphoneassistant.ui.widget;

import android.content.Context;

import org.crazyit.myphoneassistant.bean.AppDownloadInfo;
import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.common.util.AppUtils;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;

/**
 * Created by Administrator on 2018/6/19.
 */

public class DownloadButtonController {

    private RxDownload mRxDownload;

    //文件下载的目录
    private String mDownloadDir;

    public  void handClick(final DownloadProgressButton btn, final AppInfo appInfo){


        isAppInstalled(btn.getContext(),appInfo)
                .flatMap(new Function<DownloadEvent, ObservableSource<DownloadEvent>>() {
                    @Override
                    public ObservableSource<DownloadEvent> apply(DownloadEvent downloadEvent)
                            throws Exception {

                        if (DownloadFlag.UN_INSTALL==downloadEvent.getFlag()){
                            //如果是未安装状态的话,我们需要去判断文件存在不存在
                            return isApkFileExsit(appInfo);
                        }
                        return Observable.just(downloadEvent);
                    }
                }).flatMap(new Function<DownloadEvent, ObservableSource<DownloadEvent>>() {
            @Override
            public ObservableSource<DownloadEvent> apply(DownloadEvent downloadEvent) throws Exception {
                //我们需要判断是否已经存在
                if (DownloadFlag.FILE_EXIST==downloadEvent.getFlag()){
                    return  getAppDownloadInfo(appInfo).flatMap(new Function<AppDownloadInfo, ObservableSource<DownloadEvent>>() {
                        @Override
                        public ObservableSource<DownloadEvent> apply(AppDownloadInfo appDownloadInfo)
                                throws Exception {
                            return receiveDownloadStatus(appDownloadInfo) ;
                        }
                    });
                }
                return Observable.just(downloadEvent);
            }
        }).subscribe(new Consumer<DownloadEvent>() {
            @Override
            public void accept(DownloadEvent downloadEvent) throws Exception {
                int flag=downloadEvent.getFlag();
                switch (flag){
                    case DownloadFlag.INSTALLED:
                        btn.setText("运行");
                        break;
                    case DownloadFlag.STARTED:
                        btn.setProgress((int) downloadEvent.getDownloadStatus().getPercentNumber());
                        break;
                    case DownloadFlag.PAUSED:
                        btn.paused();
                        break;
                    case DownloadFlag.NORMAL:
                        btn.download();
                        break;

                }
            }
        });


    }

    public Observable<DownloadEvent> isAppInstalled(Context context,AppInfo appInfo){

        DownloadEvent event=new DownloadEvent();
        //当不能创造参数的时候可以通过参数传递来达成目的
        event.setFlag(AppUtils.isInstalled(context,appInfo.getPackageName())? DownloadFlag.INSTALLED:DownloadFlag.UN_INSTALL);


        return Observable.just(event);
    }

    public  Observable<DownloadEvent>  isApkFileExsit(AppInfo appInfo){

        //文件目录加上文件名所对应的哈希 就是文件目录
        String path=mDownloadDir+appInfo.getReleaseKeyHash();
        File file=new File(path);

        DownloadEvent event=new DownloadEvent();

        event.setFlag(file.exists()? DownloadFlag.FILE_EXIST:DownloadFlag.NORMAL);

        return Observable.just(event);
    }
    //用来获取状态
    public Observable<DownloadEvent>  receiveDownloadStatus(AppDownloadInfo appinfo){
       //// getAppDownloadInfo();
        return  mRxDownload.receiveDownloadStatus(appinfo.getDownloadUrl());
    }


    public  Observable<AppDownloadInfo> getAppDownloadInfo(AppInfo appInfo){
        return null;
    }
}
