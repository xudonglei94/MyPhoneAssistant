package org.crazyit.myphoneassistant.di.module;

import org.crazyit.myphoneassistant.data.CategoryModel;
import org.crazyit.myphoneassistant.data.LoginModel;
import org.crazyit.myphoneassistant.data.http.ApiService;
import org.crazyit.myphoneassistant.presenter.contract.CategoryContract;
import org.crazyit.myphoneassistant.presenter.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/10.
 */
@Module
public class CategoryModule {

    private CategoryContract.CategoryView mView;

    public CategoryModule(CategoryContract.CategoryView view){
        this.mView = view;
    }

    @Provides
    public CategoryContract.CategoryView provideView(){

        return mView;
    }

    @Provides
    public CategoryContract.ICategoryModel privodeModel(ApiService apiService){
        return  new CategoryModel(apiService);
    }












}
