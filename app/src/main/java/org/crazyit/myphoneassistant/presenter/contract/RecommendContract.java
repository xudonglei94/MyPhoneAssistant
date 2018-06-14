package org.crazyit.myphoneassistant.presenter.contract;

import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.bean.IndexBean;
import org.crazyit.myphoneassistant.ui.BaseView;
import org.crazyit.myphoneassistant.presenter.BasePresenter;

import java.util.List;

/**
 * Created by Administrator on 2018/6/9.
 */

public interface RecommendContract {

    interface View extends BaseView{


        void showResult(IndexBean indexBean);
//        void showNodata();
//        void showError(String msg);

        void onRequestPermissonSuccess();
        void onRequestPermissonError();

    }
//    interface  Presenter extends BasePresenter{
//
//        public  void requestDatas();
//
//
//    }
}
