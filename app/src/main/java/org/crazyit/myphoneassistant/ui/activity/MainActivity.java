package org.crazyit.myphoneassistant.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.User;
import org.crazyit.myphoneassistant.common.Constant;
import org.crazyit.myphoneassistant.common.ImageLoader.GlideCircleTransform;
import org.crazyit.myphoneassistant.common.font.MyPhoneFont;
import org.crazyit.myphoneassistant.common.rx.RxBus;
import org.crazyit.myphoneassistant.common.rx.RxHttpResponseCompat;
import org.crazyit.myphoneassistant.common.util.ACache;
import org.crazyit.myphoneassistant.common.util.PermissionUtil;
import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.di.component.DaggerMainComponent;
import org.crazyit.myphoneassistant.di.module.MainModule;
import org.crazyit.myphoneassistant.presenter.MainPresenter;
import org.crazyit.myphoneassistant.presenter.contract.MainContract;
import org.crazyit.myphoneassistant.ui.adapter.ViewPageAdapter;
import org.crazyit.myphoneassistant.ui.bean.FragmentInfo;
import org.crazyit.myphoneassistant.ui.fragment.CategoryFragment;
import org.crazyit.myphoneassistant.ui.fragment.GamesFragment;
import org.crazyit.myphoneassistant.ui.fragment.RecommendFragment;
import org.crazyit.myphoneassistant.ui.fragment.TopListFragment;
import org.crazyit.myphoneassistant.ui.widget.BadgeActionProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private  View headView;
    private ImageView mUserHeadView;
    private TextView mTextUserName;

    private  BadgeActionProvider badgeActionProvider;


    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);




    }

    @Override
    public void init() {
        RxBus.getDefault().toObservable(User.class).subscribe(new Consumer<User>() {
            @Override
            public void accept(User user) throws Exception {
                initUserHeadView(user);
            }
        });

       mPresenter.requestPermisson();

       mPresenter.getAppUpdateInfo();

    }

    private List<FragmentInfo> initFragments(){
        List<FragmentInfo>  mFragments=new ArrayList<>(4);
        mFragments.add(new FragmentInfo("推荐",RecommendFragment.class));
        mFragments.add(new FragmentInfo("排行",TopListFragment.class));
        mFragments.add(new FragmentInfo("游戏",GamesFragment.class));
        mFragments.add(new FragmentInfo("分类",CategoryFragment.class));
        return mFragments;
    }

    private void initTabLayout() {


        PagerAdapter pagerAdapter=new ViewPageAdapter(getSupportFragmentManager(),initFragments());
        viewPager.setOffscreenPageLimit(pagerAdapter.getCount());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initToolbar(){

        toolBar.inflateMenu(R.menu.toolbar_menu);

        //这个地方现在就是失效了
//        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                if(item.getItemId() == R.id.action_download){
//
//                    startActivity(new Intent(MainActivity.this,AppManagerActivity.class));
//                }
//
//                return true;
//            }
//        });
        MenuItem downloadMenuItem=toolBar.getMenu().findItem(R.id.action_download);

        badgeActionProvider= (BadgeActionProvider) MenuItemCompat.getActionProvider(downloadMenuItem);

        badgeActionProvider.setIcon(DrawableCompat.wrap(new IconicsDrawable(this, MyPhoneFont.Icon.cniao_download).color(ContextCompat.getColor(this,R.color.white))));

        badgeActionProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,AppManagerActivity.class);
                if (badgeActionProvider.getBadgeNum()>0){

                    intent.getIntExtra(Constant.POSITION,2);
                }
                startActivity(intent);

            }
        });



    }


    private void initDrawerLayout(){
         headView=navigationView.getHeaderView(0);

        mUserHeadView = (ImageView) headView.findViewById(R.id.img_avatar);
        mUserHeadView.setImageDrawable(new IconicsDrawable(this, MyPhoneFont.Icon.cniao_head).colorRes(R.color.white));

        mTextUserName = (TextView) headView.findViewById(R.id.txt_username);

        navigationView.getMenu().findItem(R.id.menu_app_update).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_loop));
        navigationView.getMenu().findItem(R.id.menu_download_manager).setIcon(new IconicsDrawable(this, MyPhoneFont.Icon.cniao_download));
        navigationView.getMenu().findItem(R.id.menu_app_uninstall).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_trash_outline));
        navigationView.getMenu().findItem(R.id.menu_setting).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_gear_outline));

        navigationView.getMenu().findItem(R.id.menu_logout).setIcon(new IconicsDrawable(this, MyPhoneFont.Icon.cniao_shutdown));

         navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 switch (item.getItemId()){
                     case R.id.menu_logout:
                         //退出登录
                         logout();
                         break;
                     case R.id.menu_download_manager:

                         toAppManagerActivity();

                         break;
                 }
                 return false;
             }
         });

         toolBar.inflateMenu(R.menu.toolbar_menu);


         ActionBarDrawerToggle mDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolBar,R.string.open,R.string.close);
         mDrawerToggle.syncState();//初始化状态
         //通过查看源码可以发现mDrawerToggle继承了DrawerLayout.DrawerListener 所以不用再在
         //drawerLayout.addDrawerListener();方法中再传入DrawerLayout.DrawerListener了传入mDrawerToggle即可
         drawerLayout.addDrawerListener(mDrawerToggle);
     }
    private void logout() {

        ACache aCache = ACache.get(this);

        aCache.put(Constant.TOKEN,"");
        aCache.put(Constant.USER,"");

        mUserHeadView.setImageDrawable(new IconicsDrawable(this, MyPhoneFont.Icon.cniao_head).colorRes(R.color.white));
        mTextUserName.setText("未登录");

        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });

        Toast.makeText(MainActivity.this,"您已退出登录",Toast.LENGTH_LONG).show();
    }

    private void initUserHeadView(User user){

        Glide.with(this).load(user.getLogo_url()).transform(new GlideCircleTransform(this)).into(mUserHeadView);

        mTextUserName.setText(user.getUsername());
    }

    private void initUser(){

        //判断我们的Acach里面到底有没有数据
        Object objUser= ACache.get(this).getAsObject(Constant.USER);

        //如果它等于空的话那我们便什么也不干
        if(objUser ==null){
            headView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
            });

        }
        else{

            User user = (User) objUser;
            initUserHeadView(user);

        }


    }
    private void toAppManagerActivity(){
        startActivity(new Intent(MainActivity.this,AppManagerActivity.class));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RxBus.get().unregis ter(this);
        //即使没有提供销毁的方法也没有关系因为它是一个单例的.
    }


    @Override
    public void requestPermissonSuccess() {
        initToolbar();
        initDrawerLayout();
        initTabLayout();
        initUser();

    }

    @Override
    public void requestPermissonFail() {
        Toast.makeText(MainActivity.this,"授权失败....",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void changeAppNeedUpdateCount(int count) {


        if(count>0){
            badgeActionProvider.setText(count+"");
        }
        else{
            badgeActionProvider.hideBadge();
        }


    }
}
