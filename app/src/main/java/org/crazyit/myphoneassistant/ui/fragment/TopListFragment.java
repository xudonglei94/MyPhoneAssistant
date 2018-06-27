package org.crazyit.myphoneassistant.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.bean.PageBean;
import org.crazyit.myphoneassistant.di.component.AppComponent;

import org.crazyit.myphoneassistant.di.component.DaggerAppInfoComponent;
import org.crazyit.myphoneassistant.di.module.AppInfoModule;
import org.crazyit.myphoneassistant.presenter.AppInfoPresenter;
import org.crazyit.myphoneassistant.presenter.contract.AppInfoContract;
import org.crazyit.myphoneassistant.ui.adapter.AppInfoAdapter;
import org.crazyit.myphoneassistant.ui.widget.DividerItemDecoration;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/6/7.
 */

public class TopListFragment extends BaseAppInfoFragment {


    @Override
    int type() {
        return AppInfoPresenter.TOP_LIST;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return  AppInfoAdapter.builder().showPosition(true).showBrief(false).showCategoryName(true).rxDownload(mRxDownload).build();
    }


    @Override
    public void setupActivityComponent(AppComponent appComponent) {

        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this))
                .build().injectTopListFragment(this);

    }


}

