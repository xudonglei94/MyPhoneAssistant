package org.crazyit.myphoneassistant.di.module;

import org.crazyit.myphoneassistant.data.SubjectModel;
import org.crazyit.myphoneassistant.data.http.ApiService;
import org.crazyit.myphoneassistant.di.FragmentScope;
import org.crazyit.myphoneassistant.presenter.contract.SubjectContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/28.
 */

@Module
public class SubjectModule {


    private SubjectContract.SubjectView mView;


    public SubjectModule(SubjectContract.SubjectView view){
        this.mView = view;
    }



    @FragmentScope
    @Provides
    public SubjectContract.ISubjectModel provideModel(ApiService apiService){

        return  new SubjectModel(apiService);
    }



    @FragmentScope
    @Provides
    public SubjectContract.SubjectView provideView(){


        return  mView;
    }
}
