package org.crazyit.myphoneassistant.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.crazyit.myphoneassistant.ui.bean.FragmentInfo;
import org.crazyit.myphoneassistant.ui.fragment.CategoryFragment;
import org.crazyit.myphoneassistant.ui.fragment.GamesFragment;
import org.crazyit.myphoneassistant.ui.fragment.TopListFragment;
import org.crazyit.myphoneassistant.ui.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/7.
 */

public class ViewPageAdapter extends FragmentStatePagerAdapter {


    private List<FragmentInfo> mFragments=new ArrayList<>(4);

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments(){
        mFragments.add(new FragmentInfo("推荐",RecommendFragment.class));
        mFragments.add(new FragmentInfo("排行",TopListFragment.class));
        mFragments.add(new FragmentInfo("游戏",GamesFragment.class));
        mFragments.add(new FragmentInfo("目录",CategoryFragment.class));
    }

    @Override
    public Fragment getItem(int position) {
        Fragment  mFragment=null;
        switch (position){
            case 0:
                mFragment=new RecommendFragment();
                break;
            case 1:
                mFragment=new TopListFragment();
                break;
            case 2:
                mFragment=new GamesFragment();
                break;
            case 3:
                mFragment=new CategoryFragment();
                break;
        }
        return mFragment;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
