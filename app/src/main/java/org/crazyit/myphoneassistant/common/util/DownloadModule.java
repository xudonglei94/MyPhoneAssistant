package org.crazyit.myphoneassistant.common.util;

import android.app.Application;
import android.os.Environment;

import org.crazyit.myphoneassistant.common.Constant;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import zlc.season.rxdownload2.RxDownload;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

/**
 * Created by Administrator on 2018/6/20.
 */

@Module
public class DownloadModule {

    @Provides
    @Singleton
    //因为AppModule中提供的是Application所以这里要和它类型保持一致
    public RxDownload provideRxDownload(Application application, Retrofit retrofit, File downDir){


        ACache.get(application).put(Constant.APK_DOWNLOAD_DIR,downDir.getPath());

        return RxDownload.getInstance(application)
                .defaultSavePath(downDir.getPath())
                .retrofit(retrofit)
                .maxDownloadNumber(10)
                .maxThread(10);
    }


    @Singleton
    @Provides
//    @FileType("download")
    File provideDownloadDir(){

        return Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS);

    }



}