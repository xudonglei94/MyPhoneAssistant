package org.crazyit.myphoneassistant.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.bean.PageBean;
import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.presenter.AppInfoPresenter;
import org.crazyit.myphoneassistant.presenter.contract.AppInfoContract;
import org.crazyit.myphoneassistant.ui.adapter.AppInfoAdapter;
import org.crazyit.myphoneassistant.ui.widget.DividerItemDecoration;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/6/14.
 */

public abstract class BaseAppInfoFragment extends  ProgressFragment<AppInfoPresenter> implements AppInfoContract.AppInfoView
        ,BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    protected  AppInfoAdapter mAdapter;

    int page=0;

    @Override
    public void init() {
        mPresenter.requestData(type(),page);
//        mPresenter.getTopListApps(page);


        initRecyclerView();

    }
    protected void initRecyclerView(){

        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()) );

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST);

        recycleView.addItemDecoration(itemDecoration);
        //我们的Adapter是动态的变得通过我们的子类来动态的改变
        mAdapter=buildAdapter();

        mAdapter.setOnLoadMoreListener(this);
        recycleView.setAdapter(mAdapter);

    }
    //type是需要子类给我们传的因此提供一个抽象方法
    abstract  int type();

    abstract  AppInfoAdapter buildAdapter();

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

//    @Override
//    public void setupActivityComponent(AppComponent appComponent) {
////        DaggerTopListComponent.builder().appComponent(appComponent).topListModule(new AppInfoModule(this))
////                .build().inject(this);
//
//    }

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
        mPresenter.requestData(type(),page);
    }
}
