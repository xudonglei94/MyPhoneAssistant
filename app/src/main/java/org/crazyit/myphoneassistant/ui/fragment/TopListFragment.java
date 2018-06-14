package org.crazyit.myphoneassistant.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.bean.PageBean;
import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.di.component.DaggerRecommendComponent;
import org.crazyit.myphoneassistant.di.component.DaggerTopListComponent;
import org.crazyit.myphoneassistant.di.module.TopListModule;
import org.crazyit.myphoneassistant.presenter.TopListPresenter;
import org.crazyit.myphoneassistant.presenter.contract.AppInfoContract;
import org.crazyit.myphoneassistant.ui.adapter.AppInfoAdapter;
import org.crazyit.myphoneassistant.ui.widget.DividerItemDecoration;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/6/7.
 */

public class TopListFragment extends ProgressFragment<TopListPresenter> implements AppInfoContract.TopListView,BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    private  AppInfoAdapter mAdapter;

    int page=0;

    @Override
    public void init() {
        mPresenter.getTopListApps(page);


        initRecyclerView();

    }
    private void initRecyclerView(){

        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()) );

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST);

        recycleView.addItemDecoration(itemDecoration);

        mAdapter=AppInfoAdapter.builder().showPosition(true).showBrief(false).showCategoryName(true).build();
        mAdapter.setOnLoadMoreListener(this);
        recycleView.setAdapter(mAdapter);



    }

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerTopListComponent.builder().appComponent(appComponent).topListModule(new TopListModule(this))
                .build().inject(this);

    }

    @Override
    public void showResult(PageBean<AppInfo> pageBean) {
        mAdapter.addData(pageBean.getDatas());

        if (pageBean.isHasMore()){
            page++;
        }
        mAdapter.setEnableLoadMore(pageBean.isHasMore());
    }

    // 下拉加载如果完成了会调用这个方法
    @Override
    public void onLoadMoreComplete() {
        mAdapter.loadMoreComplete();

    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getTopListApps(page);
    }
}
