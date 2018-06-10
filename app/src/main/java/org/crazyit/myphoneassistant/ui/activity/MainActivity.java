package org.crazyit.myphoneassistant.ui.activity;

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
import android.widget.Toast;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.ui.adapter.ViewPageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDrawerLayout();
        initTabLayout();


    }

    private void initTabLayout() {
        PagerAdapter pagerAdapter=new ViewPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    private void initDrawerLayout(){
         headView=navigationView.getHeaderView(0);
         headView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(MainActivity.this,"headerView clicked",Toast.LENGTH_LONG).show();

             }
         });
         navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 switch (item.getItemId()){
                     case R.id.menu_app_update:
                         Toast.makeText(MainActivity.this,"点击了应用更新",Toast.LENGTH_LONG).show();
                         break;
                     case R.id.menu_message:
                         Toast.makeText(MainActivity.this,"点击消息",Toast.LENGTH_LONG).show();
                         break;
                     case R.id.menu_setting:
                         Toast.makeText(MainActivity.this,"点击了设置",Toast.LENGTH_LONG).show();
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
}
