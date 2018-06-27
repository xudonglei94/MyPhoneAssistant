package org.crazyit.myphoneassistant.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/8.
 */

public class AppInfo implements Serializable {


    private int addTime;
    private boolean hasSameDevApp;
    private int videoId;
    private String source;
    private String versionName;
    private HdIconEntity hdIcon;
    private Float ratingScore;
    private String briefShow;
    private int developerId;
    private int fitness;
    private int id;
    private int level1CategoryId;
    private String releaseKeyHash;
    private boolean relateAppHasMore;
    private int rId;
    private int suitableType;
    private boolean briefUseIntro;
    private int ads;
    private String publisherName;
    private int level2CategoryId;
    private int position;
    private boolean favorite;
    private boolean isFavorite;
    private int appendSize;
    private List<AppInfo> relateAppInfoList;
    private String level1CategoryName;
    private boolean samDevAppHasMore;
    private String displayName;
    private String icon;
    private String changeLog;
    private String screenshot;
    private String permissionIds;
    private int ratingTotalCount;
    private int adType;
    private String web;
    private int apkSize;
    private String packageName;
    //    private List<AppInfo> appArticleInfoList;
    private String introduction;
    private String keyWords;
    private long updateTime;
    private int grantCode;
    private String detailHeaderImage;
    //    private List<AppInfo> moduleInfoList;
    private int versionCode;
    private List<Tag> appTags;
    private int diffFileSize;
    private List<AppInfo> sameDevAppInfoList;
    private String categoryId;


    private AppDownloadInfo mAppDownloadInfo;

    public AppDownloadInfo getAppDownloadInfo() {
        return mAppDownloadInfo;
    }

    public void setAppDownloadInfo(AppDownloadInfo appDownloadInfo) {
        mAppDownloadInfo = appDownloadInfo;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public void setHasSameDevApp(boolean hasSameDevApp) {
        this.hasSameDevApp = hasSameDevApp;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public void setHdIcon(HdIconEntity hdIcon) {
        this.hdIcon = hdIcon;
    }

    public void setRatingScore(Float ratingScore) {
        this.ratingScore = ratingScore;
    }

    public void setBriefShow(String briefShow) {
        this.briefShow = briefShow;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLevel1CategoryId(int level1CategoryId) {
        this.level1CategoryId = level1CategoryId;
    }

    public void setReleaseKeyHash(String releaseKeyHash) {
        this.releaseKeyHash = releaseKeyHash;
    }

    public void setRelateAppHasMore(boolean relateAppHasMore) {
        this.relateAppHasMore = relateAppHasMore;
    }

    public void setRId(int rId) {
        this.rId = rId;
    }

    public void setSuitableType(int suitableType) {
        this.suitableType = suitableType;
    }

    public void setBriefUseIntro(boolean briefUseIntro) {
        this.briefUseIntro = briefUseIntro;
    }

    public void setAds(int ads) {
        this.ads = ads;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setLevel2CategoryId(int level2CategoryId) {
        this.level2CategoryId = level2CategoryId;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public void setAppendSize(int appendSize) {
        this.appendSize = appendSize;
    }

    public void setRelateAppInfoList(List<AppInfo> relateAppInfoList) {
        this.relateAppInfoList = relateAppInfoList;
    }

    public void setLevel1CategoryName(String level1CategoryName) {
        this.level1CategoryName = level1CategoryName;
    }

    public void setSamDevAppHasMore(boolean samDevAppHasMore) {
        this.samDevAppHasMore = samDevAppHasMore;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }

    public void setRatingTotalCount(int ratingTotalCount) {
        this.ratingTotalCount = ratingTotalCount;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setApkSize(int apkSize) {
        this.apkSize = apkSize;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }


    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public void setGrantCode(int grantCode) {
        this.grantCode = grantCode;
    }

    public void setDetailHeaderImage(String detailHeaderImage) {
        this.detailHeaderImage = detailHeaderImage;
    }



    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public void setAppTags(List<Tag> appTags) {
        this.appTags = appTags;
    }

    public void setDiffFileSize(int diffFileSize) {
        this.diffFileSize = diffFileSize;
    }

    public void setSameDevAppInfoList(List<AppInfo> sameDevAppInfoList) {
        this.sameDevAppInfoList = sameDevAppInfoList;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getAddTime() {
        return addTime;
    }

    public boolean isHasSameDevApp() {
        return hasSameDevApp;
    }

    public int getVideoId() {
        return videoId;
    }

    public String getSource() {
        return source;
    }

    public String getVersionName() {
        return versionName;
    }

    public HdIconEntity getHdIcon() {
        return hdIcon;
    }

    public Float getRatingScore() {
        return ratingScore;
    }

    public String getBriefShow() {
        return briefShow;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public int getFitness() {
        return fitness;
    }

    public int getId() {
        return id;
    }

    public int getLevel1CategoryId() {
        return level1CategoryId;
    }

    public String getReleaseKeyHash() {
        return releaseKeyHash;
    }

    public boolean isRelateAppHasMore() {
        return relateAppHasMore;
    }

    public int getRId() {
        return rId;
    }

    public int getSuitableType() {
        return suitableType;
    }

    public boolean isBriefUseIntro() {
        return briefUseIntro;
    }

    public int getAds() {
        return ads;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public int getLevel2CategoryId() {
        return level2CategoryId;
    }

    public int getPosition() {
        return position;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public boolean isIsFavorite() {
        return isFavorite;
    }

    public int getAppendSize() {
        return appendSize;
    }

    public List<AppInfo> getRelateAppInfoList() {
        return relateAppInfoList;
    }

    public String getLevel1CategoryName() {
        return level1CategoryName;
    }

    public boolean isSamDevAppHasMore() {
        return samDevAppHasMore;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getIcon() {
        return icon;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public String getPermissionIds() {
        return permissionIds;
    }

    public int getRatingTotalCount() {
        return ratingTotalCount;
    }

    public int getAdType() {
        return adType;
    }

    public String getWeb() {
        return web;
    }

    public int getApkSize() {
        return apkSize;
    }

    public String getPackageName() {
        return packageName;
    }



    public String getIntroduction() {
        return introduction;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public int getGrantCode() {
        return grantCode;
    }

    public String getDetailHeaderImage() {
        return detailHeaderImage;
    }


    public int getVersionCode() {
        return versionCode;
    }

    public List<Tag> getAppTags() {
        return appTags;
    }

    public int getDiffFileSize() {
        return diffFileSize;
    }

    public List<AppInfo> getSameDevAppInfoList() {
        return sameDevAppInfoList;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public class HdIconEntity implements Serializable {
        /**
         * main : AppStore/01712d4cde311460f2415c0d2cbd6f37212d405fc
         */
        private String main;

        public void setMain(String main) {
            this.main = main;
        }

        public String getMain() {
            return main;
        }
    }

    public class  Tag implements Serializable{


        /**
         * tagId : 107
         * link : sametag/107
         * tagName : 二手
         */
        private int tagId;
        private String link;
        private String tagName;

        public void setTagId(int tagId) {
            this.tagId = tagId;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public int getTagId() {
            return tagId;
        }

        public String getLink() {
            return link;
        }

        public String getTagName() {
            return tagName;
        }
    }
}
