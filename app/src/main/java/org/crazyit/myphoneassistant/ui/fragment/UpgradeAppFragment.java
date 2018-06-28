package org.crazyit.myphoneassistant.ui.fragment;

import android.support.v7.widget.RecyclerView;

import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.ui.adapter.AppInfoAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/6/27.
 */

public class UpgradeAppFragment extends AppManagerFragment {


    AppInfoAdapter mAdapter;



    public UpgradeAppFragment() {
        // Required empty public constructor
    }


    @Override
    public void init() {
        super.init();
        mPresenter.getUpdateApps();
    }

//    @Override
//    public void setupActivityComponent(AppComponent appComponent) {
//
//    }

    @Override
    protected RecyclerView.Adapter setupAdapter() {

        mAdapter = AppInfoAdapter.builder().updateStatus(true).rxDownload(mPresenter.getRxDowanload()).build();

        return mAdapter;
    }



    @Override
    public void showUpdateApps(List<AppInfo> appInfos) {

        mAdapter.addData(appInfos);
    }
}
