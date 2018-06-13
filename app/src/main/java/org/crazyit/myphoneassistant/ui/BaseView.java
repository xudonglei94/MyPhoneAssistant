package org.crazyit.myphoneassistant.ui;

/**
 * Created by Administrator on 2018/6/9.
 */

public interface BaseView {
    void showLoading();
    void showError(String msg);
    void dismissLoading();
}

