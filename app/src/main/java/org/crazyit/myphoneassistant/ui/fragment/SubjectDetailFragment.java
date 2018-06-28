package org.crazyit.myphoneassistant.ui.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.Subject;
import org.crazyit.myphoneassistant.bean.SubjectDetail;
import org.crazyit.myphoneassistant.common.Constant;
import org.crazyit.myphoneassistant.common.ImageLoader.ImageLoader;
import org.crazyit.myphoneassistant.ui.adapter.AppInfoAdapter;
import org.crazyit.myphoneassistant.ui.adapter.SubjectAdapter;
import org.crazyit.myphoneassistant.ui.widget.DividerItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;
import zlc.season.rxdownload2.RxDownload;

/**
 * Created by Administrator on 2018/6/28.
 */

@SuppressLint("ValidFragment")
public class SubjectDetailFragment extends BaseSubjectFragment{

    @BindView(R.id.imageview)
    ImageView mImageView;
    @BindView(R.id.txt_desc)
    TextView mtxtDesc;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    @Inject
    RxDownload mRxDownload;


    private Subject mSubject;

    private AppInfoAdapter mAdapter;


    public SubjectDetailFragment(Subject subject){

        this.mSubject = subject;

    }
    @Override
    public void init() {
        initRecycleView();

        mPresenter.getSubjectDetail(mSubject.getRelatedId());

    }

    @Override
    public void showSubjectDetail(SubjectDetail detail) {
        ImageLoader.load(Constant.BASE_IMG_URL + detail.getPhoneBigIcon(),mImageView);

        mtxtDesc.setText(detail.getDescription());

        mAdapter.addData(detail.getListApp());
    }

    private void initRecycleView() {

        mAdapter = AppInfoAdapter.builder().showBrief(false).showCategoryName(true)
                .rxDownload(mRxDownload).build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);

        mRecyclerView.addItemDecoration(itemDecoration);

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public int setLayout() {
        return R.layout.fragment_subject_detail;
    }
}
