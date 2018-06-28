package org.crazyit.myphoneassistant.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.di.component.AppComponent;

import org.crazyit.myphoneassistant.di.component.DaggerAppInfoComponent;
import org.crazyit.myphoneassistant.di.module.AppInfoModule;
import org.crazyit.myphoneassistant.presenter.AppInfoPresenter;
import org.crazyit.myphoneassistant.ui.adapter.AppInfoAdapter;

/**
 * Created by Administrator on 2018/6/7.
 */

public class GamesFragment extends BaseAppInfoFragment {



    @Override
    int type() {
        return AppInfoPresenter.GAME;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showPosition(false).showBrief(true).showCategoryName(true).build();
    }
}
