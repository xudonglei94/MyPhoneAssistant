package org.crazyit.myphoneassistant.ui.activity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.mikepenz.iconics.context.IconicsLayoutInflater;

import org.crazyit.myphoneassistant.AppApplication;
import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/6/10.
 */

public abstract class BaseActivity<T extends  BasePresenter> extends AppCompatActivity {

    private Unbinder mUnbinder;

    private AppApplication mApplication;

    //这个Presenter是泛型的我们不知道它是什么
    @Inject
    T mPresenter;

    //这里要注意调用的是protected这个权限的额方法
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        LayoutInflaterCompat.setFactory(getLayoutInflater(),new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
        //第一步我们需要去布局layout
        setContentView(setLayout());

        //这个方法会返回一个unbind对象
        //然后把ButterKnife也抽进来
       mUnbinder= ButterKnife.bind(this);


       this.mApplication= (AppApplication) getApplication();
       setupActivityComponent(mApplication.getAppComponent());
       init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder!=Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }

//    protected  void startActivity(Class c){
//        this.startActivity(new Intent(this,c));
//    }

    public abstract  int setLayout();

    public abstract void setupActivityComponent(AppComponent appComponent);
    //fragment后面都有这个方法我们可以把这个方法抽出来放到我们的父类里面
    public  abstract void init();
}
