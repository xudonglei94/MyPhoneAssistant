package org.crazyit.myphoneassistant.ui.bean;

/**
 * Created by Administrator on 2018/6/7.
 */

public class FragmentInfo {
    private Class fragment;

    public FragmentInfo( String title ,Class fragment) {
        this.fragment = fragment;
        this.title = title;
    }

    private String title;

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
