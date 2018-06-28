package org.crazyit.myphoneassistant.presenter.contract;



import org.crazyit.myphoneassistant.bean.BaseBean;
import org.crazyit.myphoneassistant.bean.PageBean;
import org.crazyit.myphoneassistant.bean.Subject;
import org.crazyit.myphoneassistant.bean.SubjectDetail;
import org.crazyit.myphoneassistant.ui.BaseView;

import io.reactivex.Observable;


/**
 * Created by Administrator on 2018/6/28.
 */

public class SubjectContract {


    public  interface   SubjectView extends BaseView {


        void showSubjects(PageBean<Subject> subjects);
        void onLoadMoreComplete();

        void showSubjectDetail(SubjectDetail detail);

    }


    public interface ISubjectModel{

        Observable<BaseBean<PageBean<Subject>>> getSubjects(int page);
        Observable<BaseBean<SubjectDetail>> getSubjectDetail(int id);

    }
}
