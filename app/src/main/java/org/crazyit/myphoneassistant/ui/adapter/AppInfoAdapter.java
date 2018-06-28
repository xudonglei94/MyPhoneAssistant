package org.crazyit.myphoneassistant.ui.adapter;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.common.Constant;
import org.crazyit.myphoneassistant.common.ImageLoader.ImageLoader;
import org.crazyit.myphoneassistant.ui.widget.DownloadButtonController;
import org.crazyit.myphoneassistant.ui.widget.DownloadProgressButton;

import java.util.List;

import zlc.season.rxdownload2.RxDownload;

/**
 * Created by Administrator on 2018/6/14.
 */

//public class AppInfoAdapter extends BaseQuickAdapter<AppInfo,BaseViewHolder> {
//
//    String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
//
//    private Builder mBuilder;
//
//    private DownloadButtonController mDownloadButtonController;
//    //建造者模式这里要改成build
//    private AppInfoAdapter(Builder builder) {
//        super(builder.layoutId);
//        this.mBuilder = builder;
//        mDownloadButtonController=new DownloadButtonController(builder.mRxDownload);
//
//        //开启动画
//        openLoadAnimation();
//    }
//
//
//    public static Builder builder(){
//
//        return new Builder();
//    }
//    @Override
//    protected void convert(BaseViewHolder helper, AppInfo item) {
//
//
//
////        TextView txtViewPosition = helper.getView(R.id.txt_position);
////        //根据build的值来决定是否显示
////        txtViewPosition.setVisibility(mBuilder.isShowPosition? View.VISIBLE:View.GONE);
////        //+1在作用在于排序从1开始不然会从0开始
////        txtViewPosition.setText(item.getPosition()+1 +". ");
////
////        TextView txtViewCategory = helper.getView(R.id.txt_category);
////        txtViewCategory.setVisibility(mBuilder.isShowCategoryName?View.VISIBLE:View.GONE);
////        txtViewCategory.setText(item.getLevel1CategoryName());
////
////        TextView txtViewBrief = helper.getView(R.id.txt_brief);
////        txtViewBrief.setVisibility(mBuilder.isShowBrief?View.VISIBLE:View.GONE);
////        txtViewBrief.setText(item.getBriefShow());
//        ImageLoader.load(Constant.BASE_IMG_URL+item.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));
//        helper.setText(R.id.txt_app_name,item.getDisplayName());
//
//
//        TextView txtViewPosition = helper.getView(R.id.txt_position);
//        if(txtViewPosition !=null) {
//            txtViewPosition.setVisibility(mBuilder.isShowPosition ? View.VISIBLE : View.GONE);
//            txtViewPosition.setText((item.getPosition() + 1) + " .");
//        }
//
//
//        TextView textViewCategoryName = helper.getView(R.id.txt_category);
//        if(textViewCategoryName !=null) {
//            textViewCategoryName.setVisibility(mBuilder.isShowCategoryName ? View.VISIBLE : View.GONE);
//            textViewCategoryName.setText(item.getLevel1CategoryName());
//        }
//
//        TextView textViewBrief = helper.getView(R.id.txt_brief);
//        if(textViewCategoryName !=null) {
//            textViewBrief.setVisibility(mBuilder.isShowBrief ? View.VISIBLE : View.GONE);
//            textViewBrief.setText(item.getBriefShow());
//        }
//
//
//        TextView textViewSize = helper.getView(R.id.txt_apk_size);
//
//        if(textViewSize !=null){
//            textViewSize.setText((item.getApkSize() / 1014 / 1024) +"Mb");
//        }
//
//
//
//        helper.addOnClickListener(R.id.btn_download);
//
//        View viewBtn  = helper.getView(R.id.btn_download);
//
//        if (viewBtn instanceof  DownloadProgressButton){
//
//            DownloadProgressButton btn = (DownloadProgressButton) viewBtn;
//            mDownloadButtonController.handClick(btn,item);
//        }
//
//
//    }
//
//    //建造者模式
//    public static class  Builder{
//
//        //是否显示位置
//        private boolean isShowPosition;
//        //是否显示名字
//        private boolean isShowCategoryName;
//        //是否显示简介
//        private boolean isShowBrief;
//
//        private RxDownload mRxDownload;
//
//
//        private int layoutId=R.layout.template_appinfo;
//
//
//
//
//        public Builder showPosition(boolean b){
//
//            this.isShowPosition =b;
//            return this;
//        }
//
//
//        public Builder showCategoryName(boolean b){
//
//            this.isShowCategoryName =b;
//            return this;
//        }
//
//
//        public Builder showBrief(boolean b){
//
//            this.isShowBrief =b;
//            return this;
//        }
//
//        public AppInfoAdapter build(){
//
//
//            return  new AppInfoAdapter(this);
//        }
//
//
//        public Builder layout(int resId){
//            this.layoutId = resId;
//            return this;
//        }
//
//        public Builder rxDownload(RxDownload rxDownload){
//            this.mRxDownload = rxDownload;
//            return this;
//        }
//
//
//
//
//
//    }
//}
public class AppInfoAdapter extends BaseQuickAdapter<AppInfo ,BaseViewHolder> {

    String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";


    private Builder mBuilder;

    private DownloadButtonController mDownloadButtonConntroller;

    private AppInfoAdapter(Builder builder) {
        super(builder.layoutId);

        this.mBuilder = builder;

        mDownloadButtonConntroller = new DownloadButtonController(builder.mRxDownload);

        openLoadAnimation();
    }


    public static Builder builder(){

        return new Builder();
    }
    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {

        ImageLoader.load(Constant.BASE_IMG_URL+item.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name,item.getDisplayName());


        helper.addOnClickListener(R.id.btn_download);
        View viewBtn  = helper.getView(R.id.btn_download);


        TextView textViewBrief = helper.getView(R.id.txt_brief);
        if(mBuilder.isUpdateStatus){
            ExpandableTextView viewChangeLog =helper.getView(R.id.view_changelog);
            viewChangeLog.setText(item.getChangeLog());


            if(textViewBrief !=null) {
                textViewBrief.setVisibility( View.VISIBLE);
                textViewBrief.setText("v"+item.getVersionName() +  "  " + (item.getApkSize() / 1014 / 1024) +"Mb");
            }

            if (viewBtn instanceof  DownloadProgressButton){

                DownloadProgressButton btn = (DownloadProgressButton) viewBtn;
                btn.setText("升级");
            }

        }
        else{


            TextView txtViewPosition = helper.getView(R.id.txt_position);
            if(txtViewPosition !=null) {
                txtViewPosition.setVisibility(mBuilder.isShowPosition ? View.VISIBLE : View.GONE);
                txtViewPosition.setText((item.getPosition() + 1) + " .");
            }


            TextView textViewCategoryName = helper.getView(R.id.txt_category);
            if(textViewCategoryName !=null) {
                textViewCategoryName.setVisibility(mBuilder.isShowCategoryName ? View.VISIBLE : View.GONE);
                textViewCategoryName.setText(item.getLevel1CategoryName());
            }


            if(textViewBrief !=null) {
                textViewBrief.setVisibility(mBuilder.isShowBrief ? View.VISIBLE : View.GONE);
                textViewBrief.setText(item.getBriefShow());
            }
            TextView textViewSize = helper.getView(R.id.txt_apk_size);
            if(textViewSize !=null){
                textViewSize.setText((item.getApkSize() / 1014 / 1024) +"Mb");
            }

            if (viewBtn instanceof  DownloadProgressButton){

                DownloadProgressButton btn = (DownloadProgressButton) viewBtn;
                mDownloadButtonConntroller.handClick(btn,item);
            }


        }


    }






    public static class  Builder{




        private boolean isShowPosition;
        private boolean isShowCategoryName;
        private boolean isShowBrief;
        private boolean isUpdateStatus;

        private RxDownload mRxDownload;


        private int layoutId=R.layout.template_appinfo;




        public Builder showPosition(boolean b){

            this.isShowPosition =b;
            return this;
        }


        public Builder showCategoryName(boolean b){

            this.isShowCategoryName =b;
            return this;
        }


        public Builder showBrief(boolean b){

            this.isShowBrief =b;
            return this;
        }

        public AppInfoAdapter build(){


            return  new AppInfoAdapter(this);
        }


        public Builder layout(int resId){
            this.layoutId = resId;
            return this;
        }

        public Builder rxDownload(RxDownload rxDownload){
            this.mRxDownload = rxDownload;
            return this;
        }
        public Builder updateStatus(boolean b){
            this.isUpdateStatus = b;
            return this;
        }





    }





}
