package org.crazyit.myphoneassistant.common.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;


/**
 * Created by Administrator on 2018/6/16.
 */

public class PermissionUtil {





    public static Observable<Boolean> requestPermisson(Context activity, String permission){


        RxPermissions rxPermissions =  RxPermissions.getInstance(activity);


        return rxPermissions.request(permission);
    }





}
