package org.crazyit.myphoneassistant.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.Button;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.common.util.DeviceUtils;
import org.crazyit.myphoneassistant.di.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class LoginActivity extends BaseActivity {


    private static final int READ_PHONE_STATE_CODE =1000 ;
    @BindView(R.id.btn)
    Button btn;


    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {

//        //使用RxPermission的步骤
//        RxPermissions rxPermissions=new RxPermissions(this);
//
//        rxPermissions.request(Manifest.permission.CAMERA)
//                .subscribe(new Action1<Boolean>() {
//                    @Override
//                    public void call(Boolean aBoolean) {
//                        if (aBoolean){
//
//                        }else {
//
//                        }
//                    }
//                });
//        //如果没有授权
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},READ_PHONE_STATE_CODE);
//        }else{
//            //已经授权成功了
//            String imei=DeviceUtils.getIMEI(this);
//            Toast.makeText(LoginActivity.this,"imei="+imei,Toast.LENGTH_SHORT).show();
//
//        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode==READ_PHONE_STATE_CODE){
//            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                String imei=DeviceUtils.getIMEI(this);
//                Toast.makeText(LoginActivity.this,"imei="+imei,Toast.LENGTH_SHORT).show();
//            }
//        }else{
//            Toast.makeText(LoginActivity.this,"用户拒绝授权",Toast.LENGTH_SHORT).show();
//        }
//    }
}
