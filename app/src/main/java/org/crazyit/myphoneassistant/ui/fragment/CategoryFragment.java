package org.crazyit.myphoneassistant.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.Category;
import org.crazyit.myphoneassistant.common.Constant;
import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.di.component.DaggerCategoryComponent;
import org.crazyit.myphoneassistant.di.component.DaggerRecommendComponent;
import org.crazyit.myphoneassistant.di.module.CategoryModule;
import org.crazyit.myphoneassistant.presenter.CategoryPresenter;
import org.crazyit.myphoneassistant.presenter.contract.CategoryContract;
import org.crazyit.myphoneassistant.ui.adapter.AppInfoAdapter;
import org.crazyit.myphoneassistant.ui.adapter.CategoryAdapter;
import org.crazyit.myphoneassistant.ui.widget.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/6/7.
 */

public  class CategoryFragment extends ProgressFragment<CategoryPresenter> implements CategoryContract.CategoryView {


    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    private CategoryAdapter mAdapter;
//    Unbinder unbinder;

    @Override
    public void init() {
        initRecyclerView();

        mPresenter.getAllCategory();

    }

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerCategoryComponent.builder().appComponent(appComponent).categoryModule(new CategoryModule(this))
                .build().inject(this);

    }

    private void initRecyclerView(){
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()) );

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST);

        recycleView.addItemDecoration(itemDecoration);
        mAdapter = new CategoryAdapter();

        recycleView.setAdapter(mAdapter);

//        recycleView.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//
//                Intent intent = new Intent(getActivity(), CategoryAppActivity.class);
//
//                intent.putExtra(Constant.CATEGORY,mAdapter.getData().get(position));
//
//                startActivity(intent);
//
//            }
//        });

    }
//    abstract  int type();
//    abstract AppInfoAdapter buildAdapter();

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }

    @Override
    public void showData(List<Category> categories) {
        mAdapter.addData(categories);

    }
}
