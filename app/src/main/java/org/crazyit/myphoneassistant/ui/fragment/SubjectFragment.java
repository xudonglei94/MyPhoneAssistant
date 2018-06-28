package org.crazyit.myphoneassistant.ui.fragment;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.PageBean;
import org.crazyit.myphoneassistant.bean.Subject;
import org.crazyit.myphoneassistant.common.rx.RxBus;
import org.crazyit.myphoneassistant.ui.adapter.SubjectAdapter;
import org.crazyit.myphoneassistant.ui.widget.SpaceItemDecoration2;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/6/28.
 */

public class SubjectFragment extends BaseSubjectFragment implements  BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    private SubjectAdapter mAdapter;
    int page=0;
    @Override
    public void init() {
        initRecyclerView();
        mPresenter.getSubjects(page);
    }

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }
    protected void initRecyclerView(){


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setLayoutManager(layoutManager);

        SpaceItemDecoration2 dividerDecoration = new SpaceItemDecoration2(5);
        mRecyclerView.addItemDecoration(dividerDecoration);


        mAdapter = new SubjectAdapter();

        mAdapter.setOnLoadMoreListener(this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                Subject subject = mAdapter.getItem(position);

                RxBus.getDefault().post(subject);

            }
        });

    }

    @Override
    public void showSubjects(PageBean<Subject> subjects) {
        mAdapter.addData(subjects.getDatas());

        if(subjects.isHasMore()){
            //如果还有下一页的话就再加一页
            page++;
        }
        //设置一下loadMore的开关
        mAdapter.setEnableLoadMore(subjects.isHasMore());
    }

    @Override
    public void onLoadMoreRequested() {

        mPresenter.getSubjects(page);
    }
}
