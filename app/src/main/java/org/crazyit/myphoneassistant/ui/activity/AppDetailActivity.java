package org.crazyit.myphoneassistant.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.common.Constant;
import org.crazyit.myphoneassistant.common.ImageLoader.ImageLoader;
import org.crazyit.myphoneassistant.common.util.DensityUtil;
import org.crazyit.myphoneassistant.di.component.AppComponent;

import org.crazyit.myphoneassistant.presenter.AppDetailPresenter;

import org.crazyit.myphoneassistant.ui.fragment.AppDetailFragment;

import butterknife.BindView;


public class AppDetailActivity extends BaseActivity<AppDetailPresenter> {

    @BindView(R.id.view_temp)
    View mViewTemp;

    @BindView(R.id.view_content)
    FrameLayout mViewContent;

    @BindView(R.id.view_coordinator)
    CoordinatorLayout mCoordinatorLayout;

    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.txt_name)
    TextView txtName;

    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    private AppInfo  appInfo;

    @Override
    public int setLayout() {
        return R.layout.activity_app_detail;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {



    }

    @Override
    public void init() {
        appInfo= (AppInfo) getIntent().getSerializableExtra("appinfo");

        ImageLoader.load(Constant.BASE_IMG_URL+appInfo.getIcon(),imgIcon);
        txtName.setText(appInfo.getDisplayName());

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
                finish();
            }
        });


        View view = mApplication.getView();
        Bitmap bitmap=getViewImageCache(view);
        if (bitmap!=null){
            mViewTemp.setBackgroundDrawable(new BitmapDrawable(bitmap));
        }
        //确定一个固定位置的要素一个是top一个是left
        int [] location=new int[2];
        view.getLocationOnScreen(location);

        // 根据这两个值来确定它在屏幕上的位置
        int left=location[0];
        int top=location[1];



        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(mViewTemp.getLayoutParams());
        //这个地方需要减去装填栏的值不然会向下有个漂移!
        marginLayoutParams.topMargin=top-DensityUtil.getStatusBarH(this);
        marginLayoutParams.leftMargin=left;
        marginLayoutParams.width=view.getWidth();
        marginLayoutParams.height=view.getHeight();
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(marginLayoutParams);

        mViewTemp.setLayoutParams(params);

        open();



    }
    private  void open(){

        int h=DensityUtil.getScreenH(this);

        ObjectAnimator animator=ObjectAnimator.ofFloat(mViewTemp,"scaleY",1f,(float)h);

        animator.setStartDelay(500);
        animator.setDuration(1000);
        //需要加一个监听
        //不然没有凸出的效果凸出的效果会被白色所代替
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mViewTemp.setVisibility(View.GONE);
                mCoordinatorLayout.setVisibility(View.VISIBLE);


                initFragment();


            }

            @Override
            public void onAnimationStart(Animator animation) {
                //在拉伸的时候将它的背景设为白色
                mViewTemp.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });

        animator.start();
    }

    private Bitmap getViewImageCache(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();
        if (bitmap == null)
            return null;
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
        view.destroyDrawingCache();
        return bitmap;

    }


    private void initFragment(){
        AppDetailFragment fragment=new AppDetailFragment(appInfo.getId());
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.add(R.id.view_content,fragment);
        transaction.commitAllowingStateLoss();

    }


}
