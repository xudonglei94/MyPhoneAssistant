package org.crazyit.myphoneassistant.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.Subject;
import org.crazyit.myphoneassistant.common.Constant;

/**
 * Created by Administrator on 2018/6/28.
 */

public class SubjectAdapter extends BaseQuickAdapter<Subject,BaseViewHolder> {

    public SubjectAdapter() {
        super(R.layout.template_imageview);
    }
    @Override
    protected void convert(BaseViewHolder helper, Subject item) {
        ImageView imageView =  helper.getView(R.id.imageview);
        String url = Constant.BASE_IMG_URL+item.getMticon();
        Glide.with(mContext).load(url).into(imageView);

    }
}
