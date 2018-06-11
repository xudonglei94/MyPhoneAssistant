package org.crazyit.myphoneassistant.ui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/11.
 */

public class GuideFragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragment;


    public GuideFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setmFragment(List<Fragment> mFragment) {
        //这样在getCount()方法里面才不会报空指针异常
        if (mFragment==null){
            mFragment=new ArrayList<>();
        }
        this.mFragment = mFragment;
    }

    @Override
    public Fragment getItem(int position) {

        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }
}
