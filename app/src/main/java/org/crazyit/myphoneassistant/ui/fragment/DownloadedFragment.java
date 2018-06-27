package org.crazyit.myphoneassistant.ui.fragment;

import android.support.v7.widget.RecyclerView;

import org.crazyit.myphoneassistant.common.apkparset.AndroidApk;
import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.di.component.DaggerAppManagerComponent;
import org.crazyit.myphoneassistant.di.module.AppManagerModule;
import org.crazyit.myphoneassistant.ui.adapter.AndroidApkAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/6/20.
 */

//public class DownloadedFragment  extends AppManagerFragment {
//
//    AndroidApkAdapter mAdapter;
//    @Override
//    public void init() {
//        super.init();
//
//
//        mPresenter.getLocalApks();
//    }
//
//    @Override
//    public void setupActivityComponent(AppComponent appComponent) {
//        DaggerAppManagerComponent.builder().appComponent(appComponent).appManagerModule(new AppManagerModule(this))
//                .build().injectDownloaded(this);
//
//    }
//
//    @Override
//    protected RecyclerView.Adapter setupAdapter() {
////按钮状态文字变化还有adapter都需要去做
//        mAdapter=new AndroidApkAdapter(AndroidApkAdapter.FLAG_APK);
//        return mAdapter;
//    }
//
//
//
//
//    @Override
//    public void showApps(List<AndroidApk> apps) {
//        super.showApps(apps);
//    }
//}
public class DownloadedFragment extends AppManagerFragment {




    AndroidApkAdapter mAdapter;

    @Override
    public void init() {
        super.init();


        mPresenter.getLocalApks();
    }

    @Override
    protected RecyclerView.Adapter setupAdapter() {
        mAdapter = new AndroidApkAdapter(AndroidApkAdapter.FLAG_APK);

        return mAdapter;
    }


    @Override
    public void setupActivityComponent(AppComponent appComponent) {

        DaggerAppManagerComponent.builder()
                .appComponent(appComponent)
                .appManagerModule(new AppManagerModule(this))
                .build().injectDownloaded(this);

    }


    @Override
    public void showApps(List<AndroidApk> apps) {
        mAdapter.addData(apps);
    }
}