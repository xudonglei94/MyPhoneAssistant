package org.crazyit.myphoneassistant.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
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
import org.crazyit.myphoneassistant.ui.adapter.ViewPageAdapter;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

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


    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        RxBus.getDefault().toObservable(User.class).subscribe(new Consumer<User>() {
            @Override
            public void accept(User user) throws Exception {
                initUserHeadView(user);
            }
        });

        PermissionUtil.requestPermisson(this, Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {

                        if(aBoolean){
                            initDrawerLayout();

                            initTabLayout();

                            initUser();
                        }else {
                            //------
                        }
                    }
                });

    }

    private void initTabLayout() {
        PagerAdapter pagerAdapter=new ViewPageAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(pagerAdapter.getCount());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RxBus.get().unregister(this);
        //即使没有提供销毁的方法也没有关系因为它是一个单例的.
    }


}
