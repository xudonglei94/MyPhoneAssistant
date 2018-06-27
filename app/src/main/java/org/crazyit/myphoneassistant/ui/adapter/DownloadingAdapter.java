package org.crazyit.myphoneassistant.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.common.Constant;
import org.crazyit.myphoneassistant.common.ImageLoader.ImageLoader;
import org.crazyit.myphoneassistant.ui.widget.DownloadButtonController;
import org.crazyit.myphoneassistant.ui.widget.DownloadProgressButton;

import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by Administrator on 2018/6/20.
 */

//public class DownloadingAdapter extends BaseQuickAdapter<DownloadRecord,BaseViewHolder> {
//
//
//
//
//    private DownloadButtonController mDownloadButtonController;
//
//    public DownloadingAdapter(RxDownload rxDownload) {
//        super(R.layout.template_app_downloading);
//        mDownloadButtonController = new DownloadButtonController(rxDownload);
//
//        openLoadAnimation();
//    }
//
//
//
//    @Override
//    protected void convert(BaseViewHolder helper, DownloadRecord item) {
//
//
//        AppInfo appInfo = mDownloadButtonController.downloadRecord2AppInfo(item);
//
//
//        ImageLoader.load(Constant.BASE_IMG_URL+appInfo.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));
//        helper.setText(R.id.txt_app_name,appInfo.getDisplayName());
//
//
//        helper.addOnClickListener(R.id.btn_download);
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
//
//
//
//
//
//
//}
public class DownloadingAdapter extends BaseQuickAdapter<DownloadRecord,BaseViewHolder> {




    private DownloadButtonController mDownloadButtonConntroller;

    public DownloadingAdapter(RxDownload rxDownload) {
        super(R.layout.template_app_downloading);
        mDownloadButtonConntroller = new DownloadButtonController(rxDownload);

        openLoadAnimation();
    }



    @Override
    protected void convert(BaseViewHolder helper, DownloadRecord item) {


        AppInfo appInfo = mDownloadButtonConntroller.downloadRecord2AppInfo(item);


        ImageLoader.load(Constant.BASE_IMG_URL+appInfo.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name,appInfo.getDisplayName());


        helper.addOnClickListener(R.id.btn_download);
        View viewBtn  = helper.getView(R.id.btn_download);

        if (viewBtn instanceof  DownloadProgressButton){

            DownloadProgressButton btn = (DownloadProgressButton) viewBtn;
            mDownloadButtonConntroller.handClick(btn,item);
        }


    }







}
