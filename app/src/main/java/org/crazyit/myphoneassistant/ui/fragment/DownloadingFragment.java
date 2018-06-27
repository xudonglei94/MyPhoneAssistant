package org.crazyit.myphoneassistant.ui.fragment;

import android.support.v7.widget.RecyclerView;

import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.di.component.DaggerAppManagerComponent;
import org.crazyit.myphoneassistant.di.module.AppManagerModule;
import org.crazyit.myphoneassistant.ui.adapter.DownloadingAdapter;

import java.util.List;

import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by Administrator on 2018/6/20.
 */
//接口加抽象的这也是一种设计模式
public class DownloadingFragment  extends AppManagerFragment {



    private DownloadingAdapter mAdapter;

    @Override
    public void init() {
        super.init();


        mPresenter.getDownlodingApps();
    }

    @Override
    protected RecyclerView.Adapter setupAdapter() {
        mAdapter = new DownloadingAdapter(mPresenter.geRxDowanload());
        return mAdapter;
    }


    @Override
    public void setupActivityComponent(AppComponent appComponent) {

        DaggerAppManagerComponent.builder().appComponent(appComponent).appManagerModule(new AppManagerModule(this))
                .build().inject(this);

    }

    @Override
    public void showDownloading(List<DownloadRecord> downloadRecords) {

        mAdapter.addData(downloadRecords);
    }
}