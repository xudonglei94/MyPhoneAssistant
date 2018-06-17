package org.crazyit.myphoneassistant.ui.fragment;

import android.annotation.SuppressLint;

import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.di.component.DaggerAppInfoComponent;
import org.crazyit.myphoneassistant.di.module.AppInfoModule;
import org.crazyit.myphoneassistant.ui.adapter.AppInfoAdapter;

/**
 * Created by Administrator on 2018/6/17.
 */


@SuppressLint("ValidFragment")
public class CategoryAppFragment extends  BaseAppInfoFragment {



    private int categoryId;
    private int mFlagType;

    @SuppressLint("ValidFragment")
    public   CategoryAppFragment(int categoryId, int flagType){
        this.categoryId=categoryId;
        this.mFlagType=flagType;

    }

//    public  static CategoryAppFragment newInstance(int categoryId,int fragmentType){
//        return new CategoryAppFragment(categoryId,fragmentType);
//
//    }

    @Override
    public void init() {
        mPresenter.requestCategoryApps(categoryId,page,mFlagType);
        initRecyclerView();

    }

    //这里面写什么都无所谓
    @Override
    int type() {
        return 0;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showPosition(false).showBrief(true).showCategoryName(false).build();
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this))
                .build().injectCategoryAppFragment(this);

    }
}
