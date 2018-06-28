package org.crazyit.myphoneassistant.data;

import org.crazyit.myphoneassistant.bean.BaseBean;
import org.crazyit.myphoneassistant.bean.PageBean;

import org.crazyit.myphoneassistant.bean.Subject;
import org.crazyit.myphoneassistant.bean.SubjectDetail;
import org.crazyit.myphoneassistant.data.http.ApiService;
import org.crazyit.myphoneassistant.presenter.contract.SubjectContract;

import io.reactivex.Observable;


/**
 * Created by Administrator on 2018/6/28.
 */

public class SubjectModel implements SubjectContract.ISubjectModel {


    private ApiService mApiService;
    public SubjectModel(ApiService apiService){
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<PageBean<Subject>>> getSubjects(int page) {
        return mApiService.subjects(page);
    }

    @Override
    public Observable<BaseBean<SubjectDetail>> getSubjectDetail(int id) {
        return mApiService.subjectDetail(id);
    }
}
