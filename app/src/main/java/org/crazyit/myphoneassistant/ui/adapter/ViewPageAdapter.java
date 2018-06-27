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

//public class ViewPageAdapter extends FragmentStatePagerAdapter {
//
//
////    private List<FragmentInfo> mFragments=new ArrayList<>(4);
//      private List<FragmentInfo> mFragments;
//
//    public ViewPageAdapter(FragmentManager fm, List<FragmentInfo> fragments) {
//        super(fm);
////        initFragments();
//
//        mFragments = fragments;
//    }
//
//
//
//    @Override
//    public Fragment getItem(int position) {
//
//        try {
//            return (Fragment) mFragments.get(position).getFragment().newInstance();
//
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        return  null;
////        Fragment  mFragment=null;
////        switch (position){
////            case 0:
////                mFragment=new RecommendFragment();
////                break;
////            case 1:
////                mFragment=new TopListFragment();
////                break;
////            case 2:
////                mFragment=new GamesFragment();
////                break;
////            case 3:
////                mFragment=new CategoryFragment();
////                break;
////        }
////        return mFragment;
//    }
//
//    @Override
//    public int getCount() {
//        return mFragments.size();
//    }
//
//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mFragments.get(position).getTitle();
//    }
//}
public class ViewPageAdapter extends FragmentStatePagerAdapter {




    private List<FragmentInfo> mFragments;


    public ViewPageAdapter(FragmentManager fm,List<FragmentInfo> fragments) {
        super(fm);

//        initFragments();

        mFragments = fragments;
    }






    @Override
    public Fragment getItem(int position) {


        try {
            return (Fragment) mFragments.get(position).getFragment().newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return  null;

//        Fragment fragment = null;
//        switch (position){
//
//            case 0:
//                fragment = new RecommendFragment();
//                break;
//
//            case 1:
//                fragment = new TopListFragment();
//                break;
//
//            case 2:
//                fragment = new GamesFragment();
//                break;
//
//            case 3:
//                fragment = new CategoryFragment();
//                break;
//
//        }
//
//        return fragment;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}

