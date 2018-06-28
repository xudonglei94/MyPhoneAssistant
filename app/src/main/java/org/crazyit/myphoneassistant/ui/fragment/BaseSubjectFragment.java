package org.crazyit.myphoneassistant.ui.fragment;

import org.crazyit.myphoneassistant.bean.PageBean;
import org.crazyit.myphoneassistant.bean.Subject;
import org.crazyit.myphoneassistant.bean.SubjectDetail;
import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.di.component.DaggerSubjectComponent;
import org.crazyit.myphoneassistant.di.module.SubjectModule;
import org.crazyit.myphoneassistant.presenter.SubjectPresenter;
import org.crazyit.myphoneassistant.presenter.contract.SubjectContract;

/**
 * Created by Administrator on 2018/6/28.
 */

public abstract class BaseSubjectFragment  extends ProgressFragment<SubjectPresenter> implements SubjectContract.SubjectView {
    @Override
    public void showSubjects(PageBean<Subject> subjects) {

    }

    @Override
    public void onLoadMoreComplete() {

    }

    @Override
    public void showSubjectDetail(SubjectDetail detail) {

    }



    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSubjectComponent.builder().appComponent(appComponent).subjectModule(new SubjectModule(this))
                .build().inject(this);


    }
}
