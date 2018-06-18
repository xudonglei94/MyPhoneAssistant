package org.crazyit.myphoneassistant.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.common.util.DensityUtil;
import org.crazyit.myphoneassistant.di.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppDetailActivity extends BaseActivity {


    @BindView(R.id.view_content)
    FrameLayout mViewContent;
    @BindView(R.id.activity_app_detail)
    LinearLayout activityAppDetail;

    @Override
    public int setLayout() {
        return R.layout.activity_app_detail;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        View view = mApplication.getView();
        Bitmap bitmap=getViewImageCache(view);
        if (bitmap!=null){
            mViewContent.setBackgroundDrawable(new BitmapDrawable(bitmap));
        }
        //确定一个固定位置的要素一个是top一个是left
        int [] location=new int[2];
        view.getLocationOnScreen(location);

        // 根据这两个值来确定它在屏幕上的位置
        int left=location[0];
        int top=location[1];



        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(mViewContent.getLayoutParams());
        //这个地方需要减去装填栏的值不然会向下有个漂移!
        marginLayoutParams.topMargin=top-DensityUtil.getStatusBarH(this);
        marginLayoutParams.leftMargin=left;
        marginLayoutParams.width=view.getWidth();
        marginLayoutParams.height=view.getHeight();
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(marginLayoutParams);

        mViewContent.setLayoutParams(params);

        open();



    }
    private  void open(){

        int h=DensityUtil.getScreenH(this);

        ObjectAnimator animator=ObjectAnimator.ofFloat(mViewContent,"scaleY",1f,(float)h);

        //需要加一个监听
        //不然没有凸出的效果凸出的效果会被白色所代替
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationStart(Animator animation) {
                //在拉伸的时候将它的背景设为白色
                mViewContent.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });
        animator.setStartDelay(1000);
        animator.setDuration(10000);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
