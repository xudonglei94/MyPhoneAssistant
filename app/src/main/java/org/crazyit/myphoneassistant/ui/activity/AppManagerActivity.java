package org.crazyit.myphoneassistant.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.common.Constant;
import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.ui.adapter.ViewPageAdapter;
import org.crazyit.myphoneassistant.ui.bean.FragmentInfo;
import org.crazyit.myphoneassistant.ui.fragment.CategoryFragment;
import org.crazyit.myphoneassistant.ui.fragment.DownloadedFragment;
import org.crazyit.myphoneassistant.ui.fragment.DownloadingFragment;
import org.crazyit.myphoneassistant.ui.fragment.GamesFragment;
import org.crazyit.myphoneassistant.ui.fragment.InstalledAppAppFragment;
import org.crazyit.myphoneassistant.ui.fragment.TopListFragment;
import org.crazyit.myphoneassistant.ui.fragment.UpgradeAppFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppManagerActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private  int position;


    private void initToolbar() {


        mToolbar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mToolbar.setTitle(R.string.manage);
    }


    @Override
    public int setLayout() {
        return R.layout.activity_download_manager;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }



    @Override
    public void init() {

        position=getIntent().getIntExtra(Constant.POSITION,0);
        initToolbar();
        initTablayout();


    }


    private void initTablayout() {




        PagerAdapter adapter = new ViewPageAdapter(getSupportFragmentManager(),initFragments());
        mViewPager.setOffscreenPageLimit(adapter.getCount());
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.setCurrentItem(position);
        mTabLayout.getTabAt(position).select();



    }



    private List<FragmentInfo> initFragments(){

        List<FragmentInfo> mFragments = new ArrayList<>(4);

        mFragments.add(new FragmentInfo("下载",DownloadingFragment.class));
        mFragments.add(new FragmentInfo ("已完成", DownloadedFragment.class));
        mFragments.add(new FragmentInfo ("升级", UpgradeAppFragment.class));
        mFragments.add(new FragmentInfo ("已安装", InstalledAppAppFragment.class));

        return  mFragments;

    }
}
