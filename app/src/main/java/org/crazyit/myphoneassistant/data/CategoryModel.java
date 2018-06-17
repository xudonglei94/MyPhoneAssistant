package org.crazyit.myphoneassistant.data;

import org.crazyit.myphoneassistant.bean.BaseBean;
import org.crazyit.myphoneassistant.bean.Category;
import org.crazyit.myphoneassistant.data.http.ApiService;
import org.crazyit.myphoneassistant.presenter.contract.CategoryContract;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2018/6/17.
 */

public class CategoryModel implements CategoryContract.ICategoryModel {

    private ApiService mApiService;

    public CategoryModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    @Override
    public Observable<BaseBean<List<Category>>> getCategories() {
        return mApiService.getCategories();
    }
}
