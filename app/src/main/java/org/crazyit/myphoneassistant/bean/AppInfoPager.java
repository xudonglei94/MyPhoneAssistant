package org.crazyit.myphoneassistant.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/28.
 */

public class AppInfoPager implements Serializable {


    /**
     * hasMore : true
     * host : http://f1.market.xiaomi.com/download/
     * listApp : []
     * thumbnail : http://t1.market.xiaomi.com/thumbnail/
     */

    private boolean hasMore;
    private String host;
    private String thumbnail;
    private List<AppInfo> listApp;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<AppInfo> getListApp() {
        return listApp;
    }

    public void setListApp(List<AppInfo> listApp) {
        this.listApp = listApp;
    }
}
