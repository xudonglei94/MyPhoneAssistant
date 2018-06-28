package org.crazyit.myphoneassistant.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/28.
 */

public class SearchResult implements Serializable {

    private boolean hasMore;

    private List<AppInfo> listApp;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<AppInfo> getListApp() {
        return listApp;
    }

    public void setListApp(List<AppInfo> listApp) {
        this.listApp = listApp;
    }
}
