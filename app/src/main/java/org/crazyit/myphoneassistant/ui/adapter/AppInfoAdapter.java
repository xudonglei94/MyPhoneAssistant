package org.crazyit.myphoneassistant.ui.adapter;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.common.ImageLoader.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2018/6/14.
 */

public class AppInfoAdapter extends BaseQuickAdapter<AppInfo,BaseViewHolder> {

    String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    private Builder mBuilder;
    //建造者模式这里要改成build
    private AppInfoAdapter(Builder builder) {
        super(R.layout.template_appinfo);
        this.mBuilder = builder;

        //开启动画
        openLoadAnimation();
    }


    public static Builder builder(){

        return new Builder();
    }
    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {

        ImageLoader.load(baseImgUrl+item.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));

        helper.setText(R.id.txt_app_name,item.getDisplayName())
                .setText(R.id.txt_brief,item.getBriefShow());


        TextView txtViewPosition = helper.getView(R.id.txt_position);
        //根据build的值来决定是否显示
        txtViewPosition.setVisibility(mBuilder.isShowPosition? View.VISIBLE:View.GONE);
        //+1在作用在于排序从1开始不然会从0开始
        txtViewPosition.setText(item.getPosition()+1 +". ");

        TextView txtViewCategory = helper.getView(R.id.txt_category);
        txtViewCategory.setVisibility(mBuilder.isShowCategoryName?View.VISIBLE:View.GONE);
        txtViewCategory.setText(item.getLevel1CategoryName());

        TextView txtViewBrief = helper.getView(R.id.txt_brief);
        txtViewBrief.setVisibility(mBuilder.isShowBrief?View.VISIBLE:View.GONE);
        txtViewBrief.setText(item.getBriefShow());



    }






    //建造者模式
    public static class  Builder{

        //是否显示位置
        private boolean isShowPosition;
        //是否显示名字
        private boolean isShowCategoryName;
        //是否显示简介
        private boolean isShowBrief;


        public Builder showPosition(boolean  b){
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

        //因为用建造者模式改成了私有的所以我们要新建一个方法
        public AppInfoAdapter build(){


            return  new AppInfoAdapter(this);
        }





    }
}