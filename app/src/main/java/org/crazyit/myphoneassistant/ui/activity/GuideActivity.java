package org.crazyit.myphoneassistant.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.ui.adapter.GuideFragmentAdapter;
import org.crazyit.myphoneassistant.ui.fragment.GuideFragment;
import org.crazyit.myphoneassistant.ui.widget.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//如果说你的Activity不是特别复杂的话那么你不要继承BaseActivity
public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.btn_enter)
    Button btnEnter;

    @BindView(R.id.activity_guide)
    RelativeLayout activityGuide;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
//    @BindView(R.id.img_icon1)
//    ImageView imgIcon1;
//    @BindView(R.id.img_icon2)
//    ImageView imgIcon2;
//    @BindView(R.id.img_icon3)
//    ImageView imgIcon3;

    private GuideFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        //其实做这个很简单只需要一个viewPage嵌到Fragment里面就可以了

        initData();
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {


        List<Fragment> fragments = new ArrayList<>();
        fragments.add(GuideFragment.newInstance(R.drawable.guide_1, R.color.color_bg_guide1, R.string.guide_1));
        fragments.add(GuideFragment.newInstance(R.drawable.guide_2, R.color.color_bg_guide2, R.string.guide_2));
        fragments.add(GuideFragment.newInstance(R.drawable.guide_3, R.color.color_bg_guide3, R.string.guide_3));

        mAdapter = new GuideFragmentAdapter(getSupportFragmentManager());
        mAdapter.setmFragment(fragments);

        viewpager.setCurrentItem(0);
        viewpager.setOffscreenPageLimit(mAdapter.getCount());
        viewpager.setAdapter(mAdapter);
        viewpager.addOnPageChangeListener(this);

//        viewpager.setPageTransformer(); //这个方法是添加动画

        indicator.setViewPager(viewpager);



    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

//        if (position==0){
//            imgIcon1.setImageResource(R.color.color_w);
//            imgIcon2.setImageResource(R.color.colorAccent);
//            imgIcon3.setImageResource(R.color.colorAccent);
//        }
//        else if(position==1){
//            imgIcon1.setImageResource(R.color.colorAccent);
//            imgIcon2.setImageResource(R.color.color_w);
//            imgIcon3.setImageResource(R.color.colorAccent);
//
//        }
//        else if (position==2){
//            imgIcon1.setImageResource(R.color.colorAccent);
//            imgIcon2.setImageResource(R.color.colorAccent);
//            imgIcon3.setImageResource(R.color.color_w);
//
//        }
        btnEnter.setVisibility((position==mAdapter.getCount()-1)? View.VISIBLE:View.GONE);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
