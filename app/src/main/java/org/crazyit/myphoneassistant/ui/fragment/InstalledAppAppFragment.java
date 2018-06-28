package org.crazyit.myphoneassistant.ui.fragment;

import android.support.v7.widget.RecyclerView;

import org.crazyit.myphoneassistant.common.apkparset.AndroidApk;
import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.di.component.DaggerAppManagerComponent;
import org.crazyit.myphoneassistant.di.module.AppManagerModule;
import org.crazyit.myphoneassistant.ui.adapter.AndroidApkAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/6/26.
 */

public class InstalledAppAppFragment extends AppManagerFragment {


    private  AndroidApkAdapter mAdapter;

    @Override
    public void init() {
        super.init();


        mPresenter.getInstalledApps();
    }
    @Override
    protected RecyclerView.Adapter setupAdapter() {

        mAdapter = new AndroidApkAdapter(AndroidApkAdapter.FLAG_APP);

        return mAdapter;
    }

//    @Override
//    public void setupActivityComponent(AppComponent appComponent) {
//
//        DaggerAppManagerComponent.builder().appManagerModule(new AppManagerModule(this))
//                .appComponent(appComponent).build().injectInstalled(this);
//
//
//
//    }


    @Override
    public void showApps(List<AndroidApk> apps) {
        mAdapter.addData(apps);
    }
}
