package org.crazyit.myphoneassistant.di.module;

import org.crazyit.myphoneassistant.data.SearchModel;
import org.crazyit.myphoneassistant.data.http.ApiService;
import org.crazyit.myphoneassistant.di.FragmentScope;
import org.crazyit.myphoneassistant.presenter.contract.SearchContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/28.
 */

@Module
public class SearchModule {


    private SearchContract.SearchView mView;


    public SearchModule(SearchContract.SearchView view){

        this.mView = view;
    }

    @FragmentScope
    @Provides
    public SearchContract.ISearchModel provideModel(ApiService apiService){

        return  new SearchModel(apiService);
    }

    @FragmentScope
    @Provides
    public SearchContract.SearchView provideView(){

        return  mView;
    }
}
