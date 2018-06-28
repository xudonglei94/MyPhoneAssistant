package org.crazyit.myphoneassistant.bean.requestbean;

/**
 * Created by Administrator on 2018/6/27.
 */

public class AppsUpdateBean {


    private String packageName;
    private String versionCode;


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }
}
