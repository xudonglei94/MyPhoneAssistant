package org.crazyit.myphoneassistant.presenter.contract;

import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.bean.BaseBean;
import org.crazyit.myphoneassistant.bean.requestbean.AppsUpdateBean;
import org.crazyit.myphoneassistant.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/6/27.
 */

public class MainContract {


    public  interface   MainView extends BaseView {


        void requestPermissonSuccess();
        void requestPermissonFail();

        void changeAppNeedUpdateCount(int count);





    }


    public interface IMainModel{


        Observable<BaseBean<List<AppInfo>>> getUpdateApps(AppsUpdateBean param);

    }
}
