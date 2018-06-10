package org.crazyit.myphoneassistant.ui.presenter.contract;

import org.crazyit.myphoneassistant.ui.bean.AppInfo;
import org.crazyit.myphoneassistant.ui.fragment.BaseView;
import org.crazyit.myphoneassistant.ui.presenter.BasePresenter;

import java.util.List;

/**
 * Created by Administrator on 2018/6/9.
 */

public interface RecommendContract {

    interface View extends BaseView{


        void showResult(List<AppInfo> datas);
        void showNodata();
        void showError(String msg);

    }
    interface  Presenter extends BasePresenter{

        public  void requestDatas();


    }
}
