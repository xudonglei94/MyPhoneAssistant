package org.crazyit.myphoneassistant.presenter;



import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import org.crazyit.myphoneassistant.ui.BaseView;

/**
 * Created by Administrator on 2018/6/9.
 */
//这里经常会用到两个东西一个是model一个是view
public class BasePresenter<M,V extends BaseView> {



    protected  M mModel;
    protected  V mView;

    protected Context mContext;

    //因为我们这里的model和view都是通过构造方法来传进来的所以我们需要去写构造方法,
    public  BasePresenter(M m,V v){
        this.mModel=m;
        this.mView=v;
        initContext();
    }
    //这个方法用来获取到Context对象的
    private  void initContext(){
        if(mView instanceof Fragment){
            mContext = ((Fragment)mView).getActivity();
        }
        else {
            mContext = (Activity) mView;
        }
    }

}
