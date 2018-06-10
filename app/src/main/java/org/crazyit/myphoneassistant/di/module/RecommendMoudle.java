package org.crazyit.myphoneassistant.di.module;

import android.app.ProgressDialog;

import org.crazyit.myphoneassistant.presenter.RecommendPresenter;
import org.crazyit.myphoneassistant.presenter.contract.RecommendContract;
import org.crazyit.myphoneassistant.data.RecommendModel;
import org.crazyit.myphoneassistant.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/10.
 */
//这里面的工作全部都是用来new东西的
//这个Module是告诉dragger2这个类是用来提供实例的
@Module
public class RecommendMoudle {

    //通过构造方法把view传进来
    private  RecommendContract.View mView;

    public  RecommendMoudle(RecommendContract.View view){
        this.mView=view;
    }

    //这个方法以此来告诉dragger2是来提供实例的
    //Provide的作用是告诉dragger2这个方法是来提供实例的
    //这里其实是两种方法来初始化这个Present
    @Provides
    public RecommendContract.Presenter providePresenter(RecommendContract.View view,RecommendModel model){

        //这个view是通过构造方式将它传递进来的
        return  new RecommendPresenter(view,model);

    }
    @Provides
    public  RecommendContract.View provideView(){
        return  mView;
    }
    @Provides
    public  RecommendModel provideModel(){
        return new RecommendModel();
    }
    @Provides
    public ProgressDialog provideProgressDialog(RecommendContract.View view){
        return  new ProgressDialog(((RecommendFragment)view).getActivity());
    }


}
