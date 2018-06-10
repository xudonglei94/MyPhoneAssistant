package org.crazyit.myphoneassistant.presenter;

import org.crazyit.myphoneassistant.ui.BaseView;

/**
 * Created by Administrator on 2018/6/9.
 */
//这里经常会用到两个东西一个是model一个是view
public class BasePresenter<M,V extends BaseView> {


    protected  M mModel;
    protected  V mView;

    //因为我们这里的model和view都是通过构造方法来传进来的所以我们需要去写构造方法,
    public  BasePresenter(M m,V v){
        this.mModel=m;
        this.mView=v;
    }

}
