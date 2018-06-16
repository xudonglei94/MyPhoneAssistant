package org.crazyit.myphoneassistant.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.LoginBean;
import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.di.component.DaggerLoginComponent;
import org.crazyit.myphoneassistant.di.module.LoginModule;
import org.crazyit.myphoneassistant.presenter.LoginPresenter;
import org.crazyit.myphoneassistant.presenter.contract.LoginContract;
import org.crazyit.myphoneassistant.ui.widget.LoadingButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView {
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.txt_mobi)
    EditText txtMobi;
    @BindView(R.id.view_mobi_wrapper)
    TextInputLayout viewMobiWrapper;
    @BindView(R.id.txt_password)
    EditText txtPassword;
    @BindView(R.id.view_password_wrapper)
    TextInputLayout viewPasswordWrapper;
    @BindView(R.id.btn_login)
    LoadingButton btnLogin;





    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder().appComponent(appComponent).loginModule(new LoginModule(this))
                .build().inject(this);

    }

    @Override
    public void init() {
        initView();

    }

    private void initView() {
        toolBar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );

        //如果用rxbinding则这么写
       Observable<CharSequence> obMobi= RxTextView.textChanges(txtMobi);
        Observable<CharSequence> obPassword= RxTextView.textChanges(txtPassword);

        Observable.combineLatest(obMobi, obPassword, new Func2<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence mobi, CharSequence pwd) {
                return isPasswordValid(pwd.toString())&&isPhoneValid(mobi.toString());
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                RxView.enabled(btnLogin).call(aBoolean);
            }
        });

        RxView.clicks(btnLogin).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                mPresenter.login(txtMobi.getText().toString().trim(),txtPassword.getText().toString().trim());

            }
        });

    }
    //判断这个phone是不是11位
     private  boolean isPhoneValid(String phone){
        return  phone.length()==11;
     }

     private  boolean isPasswordValid(String password){
         return  password.length()>=6;
     }

    @Override
    public void checkPhoneError() {
        viewMobiWrapper.setError("手机格式不正确");

    }

    @Override
    public void checkPhoneSuccess() {
        viewMobiWrapper.setError("");
        viewMobiWrapper.setErrorEnabled(false);

    }

    @Override
    public void showLoading() {
        btnLogin.showLoading();

    }

    @Override
    public void showError(String msg) {
        btnLogin.showButtonText();

    }

    @Override
    public void dismissLoading() {
        btnLogin.showButtonText();
    }



    @Override
    public void loginSuccess(LoginBean bean) {
        this.finish();


    }


//    @OnClick(R.id.btn)
//    public void onViewClicked() {
//
////        //使用RxPermission的步骤
////        RxPermissions rxPermissions=new RxPermissions(this);
////
////        rxPermissions.request(Manifest.permission.CAMERA)
////                .subscribe(new Action1<Boolean>() {
////                    @Override
////                    public void call(Boolean aBoolean) {
////                        if (aBoolean){
////
////                        }else {
////
////                        }
////                    }
////                });
////        //如果没有授权
////        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
////            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},READ_PHONE_STATE_CODE);
////        }else{
////            //已经授权成功了
////            String imei=DeviceUtils.getIMEI(this);
////            Toast.makeText(LoginActivity.this,"imei="+imei,Toast.LENGTH_SHORT).show();
////
////        }
//    }

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
