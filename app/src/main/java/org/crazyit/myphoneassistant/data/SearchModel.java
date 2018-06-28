package org.crazyit.myphoneassistant.data;

import org.crazyit.myphoneassistant.bean.BaseBean;
import org.crazyit.myphoneassistant.bean.SearchResult;
import org.crazyit.myphoneassistant.data.http.ApiService;
import org.crazyit.myphoneassistant.presenter.contract.SearchContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/6/28.
 */

public class SearchModel implements SearchContract.ISearchModel {


    private ApiService mApiService;


    public SearchModel(ApiService apiService){

        this.mApiService = apiService;
    }

    public Observable<BaseBean<List<String>>> getSuggestion(String keyword){


        return  mApiService.searchSuggest(keyword);

    }

    @Override
    public Observable<BaseBean<SearchResult>> search(String keyword) {
        return  mApiService.search(keyword);
    }
}
