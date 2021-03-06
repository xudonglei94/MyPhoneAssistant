package org.crazyit.myphoneassistant.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.common.Constant;
import org.crazyit.myphoneassistant.common.util.ACache;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomActivity extends AppCompatActivity {

    @BindView(R.id.pathView)
    PathView pathView;
    @BindView(R.id.activity_welcome)
    ConstraintLayout activityWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        ButterKnife.bind(this);

        pathView.getPathAnimator()
                .delay(100)
                .duration(5000)
                .interpolator(new AccelerateDecelerateInterpolator())
                //动画完毕需要让他进行跳转
                .listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
                    @Override
                    public void onAnimationEnd() {
                        jump();

                    }
                })
                .start();

    }

    private void  jump() {

        String isShowGuide= ACache.get(this).getAsString(Constant.IS_SHOW_GUIDE);

        //第一次启动进入引导页面
        if (null==isShowGuide){
            startActivity(new Intent(this,GuideActivity.class));
        }else
        {
            startActivity(new Intent(this,MainActivity.class));
        }
//        startActivity(new Intent(this,GuideActivity.class));
        this.finish();
    }
}
