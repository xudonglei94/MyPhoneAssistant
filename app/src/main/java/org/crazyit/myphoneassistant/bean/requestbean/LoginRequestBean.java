package org.crazyit.myphoneassistant.bean.requestbean;

/**
 * Created by Administrator on 2018/6/13.
 */

public class LoginRequestBean {

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String phone;
    private  String password;
}
