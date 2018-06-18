package org.crazyit.myphoneassistant.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.common.Constant;
import org.crazyit.myphoneassistant.common.ImageLoader.ImageLoader;
import org.crazyit.myphoneassistant.common.util.DateUtils;
import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.di.component.DaggerAppDetailComponent;
import org.crazyit.myphoneassistant.di.module.AppDetailModule;
import org.crazyit.myphoneassistant.di.module.AppModelModule;
import org.crazyit.myphoneassistant.presenter.AppDetailPresenter;
import org.crazyit.myphoneassistant.presenter.contract.AppInfoContract;
import org.crazyit.myphoneassistant.ui.adapter.AppInfoAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/6/18.
 */

@SuppressLint("ValidFragment")
public class AppDetailFragment extends ProgressFragment<AppDetailPresenter> implements AppInfoContract.AppDetailView {


    @BindView(R.id.view_gallery)
    LinearLayout viewGallery;



    //可以展开的textview
    @BindView(R.id.view_introduction)
    ExpandableTextView viewIntroduction;
    @BindView(R.id.txt_update_time)
    TextView txtUpdateTime;
    @BindView(R.id.txt_version)
    TextView txtVersion;
    @BindView(R.id.txt_apk_size)
    TextView txtApkSize;
    @BindView(R.id.txt_publisher)
    TextView txtPublisher;
    @BindView(R.id.txt_publisher2)
    TextView txtPublisher2;
    @BindView(R.id.recycler_view_same_dev)
    RecyclerView recyclerViewSameDev;
    @BindView(R.id.recycler_view_relate)
    RecyclerView recyclerViewRelate;


    private LayoutInflater mInflater;
    private int mAppId;
    private AppInfoAdapter mAdapter;

    public AppDetailFragment(int id) {
        this.mAppId = id;

    }

    @Override
    public void init() {
        mInflater = LayoutInflater.from(getActivity());
        mPresenter.getAppDetail(mAppId);
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_app_detail;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppDetailComponent.builder().appComponent(appComponent)
                .appDetailModule(new AppDetailModule(this))
                .appModelModule(new AppModelModule())
                .build().inject(this);

    }

    @Override
    public void showAppDetail(AppInfo appInfo) {
        showScreenshot(appInfo.getScreenshot());

        viewIntroduction.setText(appInfo.getIntroduction());



        txtUpdateTime.setText(DateUtils.formatDate(appInfo.getUpdateTime()));
        txtApkSize.setText((appInfo.getApkSize() / 1014 / 1024) + " Mb");
        txtVersion.setText(appInfo.getVersionName());
        txtPublisher.setText(appInfo.getPublisherName());
        txtPublisher2.setText(appInfo.getPublisherName());






        mAdapter = AppInfoAdapter.builder().layout(R.layout.template_appinfo2)
                .build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewSameDev.setLayoutManager(layoutManager);


        mAdapter.addData(appInfo.getSameDevAppInfoList());
        recyclerViewSameDev.setAdapter(mAdapter);

        /////////////////////////////////////////////

        mAdapter = AppInfoAdapter.builder().layout(R.layout.template_appinfo2)
                .build();

        recyclerViewRelate.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mAdapter.addData(appInfo.getRelateAppInfoList());
        recyclerViewRelate.setAdapter(mAdapter);


    }
    private void showScreenshot(String screenShot){
        //screenShot是通过逗号进行分隔的
        List<String> urls= Arrays.asList(screenShot.split(","));

        for (String  url:urls){
            ImageView imageView = (ImageView) mInflater.inflate(R.layout.template_imageview, viewGallery, false);
            ImageLoader.load(Constant.BASE_IMG_URL + url, imageView);
            viewGallery.addView(imageView);
        }


    }

}
