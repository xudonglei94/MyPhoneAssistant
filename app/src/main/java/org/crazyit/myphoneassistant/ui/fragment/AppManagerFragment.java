package org.crazyit.myphoneassistant.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.common.apkparset.AndroidApk;
import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.di.component.DaggerAppManagerComponent;
import org.crazyit.myphoneassistant.di.module.AppManagerModule;
import org.crazyit.myphoneassistant.presenter.AppManagerPresenter;
import org.crazyit.myphoneassistant.presenter.contract.AppManagerContract;
import org.crazyit.myphoneassistant.ui.widget.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by Administrator on 2018/6/20.
 */

public abstract class AppManagerFragment extends ProgressFragment<AppManagerPresenter>  implements AppManagerContract.AppManagerView {



    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;



    @Override
    public void init() {

        setupRecyclerView();

    }

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

        DaggerAppManagerComponent.builder()
                .appComponent(appComponent)
                .appManagerModule(new AppManagerModule(this))
                .build().inject(this);

    }

    private void setupRecyclerView() {


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()) );

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST);

        mRecyclerView.addItemDecoration(itemDecoration);


        mRecyclerView.setAdapter(setupAdapter());
    }

    @Override
    public void showApps(List<AndroidApk> apps) {

    }

    @Override
    public void showDownloading(List<DownloadRecord> downloadRecords) {

    }

    @Override
    public void showUpdateApps(List<AppInfo> appInfos) {

    }

    protected abstract RecyclerView.Adapter setupAdapter();

}
