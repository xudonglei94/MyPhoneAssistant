package org.crazyit.myphoneassistant.presenter;

import org.crazyit.myphoneassistant.bean.Category;
import org.crazyit.myphoneassistant.common.rx.RxHttpResponseCompat;
import org.crazyit.myphoneassistant.common.rx.subscriber.ProgressSubcriber;
import org.crazyit.myphoneassistant.presenter.contract.CategoryContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/17.
 */

public class CategoryPresenter extends  BasePresenter<CategoryContract.ICategoryModel,CategoryContract.CategoryView> {

    @Inject
    public CategoryPresenter(CategoryContract.ICategoryModel iCategoryModel, CategoryContract.CategoryView categoryView) {
        super(iCategoryModel, categoryView);
    }

    public  void getAllCategory(){

        mModel.getCategories().compose(RxHttpResponseCompat.<List<Category>>compatResult())
                .subscribe(new ProgressSubcriber<List<Category>>(mContext,mView) {
                    @Override
                    public void onNext(List<Category> categories) {
                        mView.showData(categories);
                    }
                });
    }


}
