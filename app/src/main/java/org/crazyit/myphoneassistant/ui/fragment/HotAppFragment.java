package org.crazyit.myphoneassistant.ui.fragment;

import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.presenter.AppInfoPresenter;
import org.crazyit.myphoneassistant.ui.adapter.AppInfoAdapter;

/**
 * Created by Administrator on 2018/6/28.
 */

public class HotAppFragment extends BaseAppInfoFragment {


    @Override
    int type() {
        return AppInfoPresenter.HOT_APP_LIST;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return  AppInfoAdapter.builder().showPosition(true).showBrief(false).showCategoryName(true).rxDownload(mRxDownload).build();
    }

//
//    @Override
//    public void setupActivityComponent(AppComponent appComponent) {
//
//    }
}
