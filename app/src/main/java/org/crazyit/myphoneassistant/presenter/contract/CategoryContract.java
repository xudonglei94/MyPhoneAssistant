package org.crazyit.myphoneassistant.presenter.contract;

import org.crazyit.myphoneassistant.bean.BaseBean;
import org.crazyit.myphoneassistant.bean.Category;
import org.crazyit.myphoneassistant.bean.LoginBean;
import org.crazyit.myphoneassistant.ui.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2018/6/15.
 */

public interface CategoryContract {
    public interface  ICategoryModel{

        public  Observable<BaseBean<List<Category>>> getCategories();

    }


    public interface  CategoryView extends BaseView {

        public void showData(List<Category> categories);




    }

}
