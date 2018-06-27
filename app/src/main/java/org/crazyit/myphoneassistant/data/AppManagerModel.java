package org.crazyit.myphoneassistant.data;

import android.content.Context;

import org.crazyit.myphoneassistant.common.Constant;
import org.crazyit.myphoneassistant.common.apkparset.AndroidApk;
import org.crazyit.myphoneassistant.common.util.ACache;
import org.crazyit.myphoneassistant.common.util.AppUtils;
import org.crazyit.myphoneassistant.presenter.contract.AppManagerContract;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by Administrator on 2018/6/20.
 */

//public class AppManagerModel implements AppManagerContract.IAppManagerModel {
//
//
//
//    private RxDownload mRxDownload;
//    private Context mContext;
//
//    public AppManagerModel(Context context,RxDownload rxDownload){
//
//        this.mContext =context;
//
//        mRxDownload = rxDownload;
//    }
//    //////
//    public AppManagerModel(RxDownload rxDownload){
//
//        mRxDownload = rxDownload;
//    }
//
//
//    @Override
//    public Observable<List<DownloadRecord>> getDownloadRecord() {
//        return mRxDownload.getTotalDownloadRecords();
//    }
//
//    @Override
//    public RxDownload getRxDownload() {
//        return mRxDownload;
//    }
//
//    @Override
//    public Observable<List<AndroidApk>> getLocalApks() {
//
//        final String dir = ACache.get(mContext).getAsString(Constant.APK_DOWNLOAD_DIR);
//
//        return Observable.create(new ObservableOnSubscribe<List<AndroidApk>>() {
//            @Override
//            public void subscribe(ObservableEmitter<List<AndroidApk>> e) throws Exception {
//
//                e.onNext(scanApks(dir));
//                e.onComplete();
//            }
//        });
//    }
//
//    @Override
//    public Observable<List<AndroidApk>> getInstalledApps() {
//
//        return Observable.create(new ObservableOnSubscribe<List<AndroidApk>>() {
//            @Override
//            public void subscribe(ObservableEmitter<List<AndroidApk>> e) throws Exception {
//                e.onNext(AppUtils.getInstalledApps(mContext));
//
//                e.onComplete();
//            }
//        });
//    }
//
//
//    //首先需要扫描文件,需要对整个目录dir进行扫描
//    //如果在写代码的时候我们不知道返回值是什么一律先填void等知道了需要返回什么值了再填上对应的返回值
//    private List<AndroidApk> scanApks(String dir){
//
//
//        File file = new File(dir);
//
//
//        if(!file.isDirectory()){
//
//            throw  new RuntimeException("is not Dir");
//        }
//
//
//
//        File[] apks =  file.listFiles(new FileFilter(){
//
//
//            @Override
//            public boolean accept(File f) {
//
//
//                if(f.isDirectory()){
//                    return  false;
//                }
//
//                return f.getName().endsWith(".apk");
//            }
//        });
//
//
//        List<AndroidApk>  androidApks = new ArrayList<>();
//
//
//        for (File apk : apks){
//
//            AndroidApk androidApk = AndroidApk.read(mContext,apk.getPath());
//
//            androidApks.add(androidApk);
//        }
//
//
//        return androidApks;
//
//
//
//
//
//
//
//
//
//    }
//
//
//}
public class AppManagerModel implements AppManagerContract.IAppManagerModel {



    private  RxDownload mRxDownload;
    private Context mContext;

    public AppManagerModel(Context context,RxDownload rxDownload){

        this.mContext =context;

        mRxDownload = rxDownload;
    }

    @Override
    public Observable<List<DownloadRecord>> getDownloadRecord() {
        return mRxDownload.getTotalDownloadRecords();
    }

    @Override
    public RxDownload getRxDownload() {
        return mRxDownload;
    }

    @Override
    public Observable<List<AndroidApk>> getLocalApks() {

        final String dir = ACache.get(mContext).getAsString(Constant.APK_DOWNLOAD_DIR);

        return Observable.create(new ObservableOnSubscribe<List<AndroidApk>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AndroidApk>> e) throws Exception {

                e.onNext(scanApks(dir));
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<List<AndroidApk>> getInstalledApps() {


        return Observable.create(new ObservableOnSubscribe<List<AndroidApk>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AndroidApk>> e) throws Exception {
                e.onNext(AppUtils.getInstalledApps(mContext));

                e.onComplete();
            }
        });
    }

    //首先需要扫描文件,需要对整个目录dir进行扫描
    //如果在写代码的时候我们不知道返回值是什么一律先填void等知道了需要返回什么值了再填上对应的返回值
    private List<AndroidApk> scanApks(String dir){


        File file = new File(dir);


        if(!file.isDirectory()){

            throw  new RuntimeException("is not Dir");
        }



        File[] apks =file.listFiles(new FileFilter(){


            @Override
            public boolean accept(File f) {


                if(f.isDirectory()){
                    return  false;
                }

                return f.getName().endsWith(".apk");
            }
        });


        List<AndroidApk>  androidApks = new ArrayList<>();

        for (File apk : apks){

            AndroidApk androidApk = AndroidApk.read(mContext,apk.getPath());
            if(androidApk !=null)
                androidApks.add(androidApk);
        }


        return androidApks;


    }


}

