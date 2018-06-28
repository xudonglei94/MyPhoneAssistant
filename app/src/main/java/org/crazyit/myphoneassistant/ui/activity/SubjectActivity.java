package org.crazyit.myphoneassistant.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.Subject;
import org.crazyit.myphoneassistant.common.rx.RxBus;
import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.di.component.DaggerSubjectComponent;
import org.crazyit.myphoneassistant.di.module.SubjectModule;
import org.crazyit.myphoneassistant.ui.fragment.SubjectDetailFragment;
import org.crazyit.myphoneassistant.ui.fragment.SubjectFragment;

public class SubjectActivity extends BaseActivity {



    public static final int FRAGMENT_SUBJECT=0;
    public static final int FRAGMENT_DETAIL=1;

    private int fragmentIndex = FRAGMENT_SUBJECT;

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    private FragmentManager mFragmentManager;

    SubjectFragment mSubjectFragment;

    SubjectDetailFragment mDetailFragment;

    @Override
    public int setLayout() {
        return R.layout.activity_subject;
    }


    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {


        mToolBar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handNavigation();
//                finish();


            }
        });



        mFragmentManager = getSupportFragmentManager();

        showSubjectFragment();

        showSubjectDetailFragmentRxBus();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handNavigation();
    }

    private void handNavigation(){

        if(fragmentIndex == FRAGMENT_SUBJECT){
            finish();
        }
        else {
            showSubjectFragment();
        }
    }

    private void showSubjectDetailFragmentRxBus() {

        RxBus.getDefault().toObservable(Subject.class).subscribe(new Consumer<Subject>() {
            @Override
            public void accept(@NonNull Subject subject) throws Exception {


                showSubjectDetailFragment(subject);


            }
        });
    }
    private void  showSubjectDetailFragment(Subject subject){

        fragmentIndex = FRAGMENT_DETAIL;
        FragmentTransaction ft = mFragmentManager.beginTransaction();

        if(mDetailFragment != null){
            ft.remove(mDetailFragment);
        }

        mDetailFragment = new SubjectDetailFragment(subject);
        ft.add(R.id.content,mDetailFragment);


        ft.commit();

        mToolBar.setTitle(subject.getTitle());
    }


    private void  showSubjectFragment(){


        fragmentIndex = FRAGMENT_SUBJECT;
        mToolBar.setTitle("主题");
        FragmentTransaction ft = mFragmentManager.beginTransaction();

        hideFragment(ft);
        if(mSubjectFragment == null){
            mSubjectFragment = new SubjectFragment();

            ft.add(R.id.content,mSubjectFragment);

        }else {
            ft.show(mSubjectFragment);
        }

        ft.commit();
//        mToolBar.setTitle("主题");


    }



    //show它之前还需要还必须要把所有的fragment进行隐藏
    private void  hideFragment(FragmentTransaction ft){

        if(mSubjectFragment != null){
            ft.hide(mSubjectFragment);
        }
        if(mDetailFragment != null){
            ft.hide(mDetailFragment);
        }


    }



}
