package org.crazyit.myphoneassistant.presenter;

import org.crazyit.myphoneassistant.bean.PageBean;
import org.crazyit.myphoneassistant.bean.Subject;
import org.crazyit.myphoneassistant.bean.SubjectDetail;
import org.crazyit.myphoneassistant.common.rx.RxHttpResponseCompat;
import org.crazyit.myphoneassistant.common.rx.subscriber.ErrorHandlerSubscriber;
import org.crazyit.myphoneassistant.common.rx.subscriber.ProgressSubcriber;
import org.crazyit.myphoneassistant.presenter.contract.SubjectContract;

import javax.inject.Inject;

import io.reactivex.Observer;

/**
 * Created by Administrator on 2018/6/28.
 */

public class SubjectPresenter extends BasePresenter<SubjectContract.ISubjectModel,SubjectContract.SubjectView> {


    @Inject
    public SubjectPresenter(SubjectContract.ISubjectModel iSubjectModel,
                            SubjectContract.SubjectView subjectView) {
        super(iSubjectModel, subjectView);
    }




    public void  getSubjects(int page){


        Observer subscriber =null;

        if(page ==0){

            subscriber= new ProgressSubcriber<PageBean<Subject>>(mContext,mView) {
                @Override
                public void onNext(PageBean<Subject> pageBean) {
                    mView.showSubjects(pageBean);
                }
            };
        }
        else{
            subscriber = new ErrorHandlerSubscriber<PageBean<Subject>>(mContext) {
                @Override
                public void onComplete() {
                    mView.onLoadMoreComplete();
                }

                @Override
                public void onNext(PageBean<Subject> pageBean) {

                    mView.showSubjects(pageBean);
                }
            };
        }

        mModel.getSubjects(page)
                .compose(RxHttpResponseCompat.<PageBean<Subject>>compatResult())
                .subscribe(subscriber);


    }



    public void getSubjectDetail(int id){


        mModel.getSubjectDetail(id).compose(RxHttpResponseCompat.<SubjectDetail>compatResult())
                .subscribe(new ProgressSubcriber<SubjectDetail>(mContext,mView) {
                    @Override
                    public void onNext(SubjectDetail subjectDetail) {

                        mView.showSubjectDetail(subjectDetail);
                    }
                });


    }










}
