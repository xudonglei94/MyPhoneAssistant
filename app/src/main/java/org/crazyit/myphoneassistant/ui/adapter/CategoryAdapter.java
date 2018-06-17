package org.crazyit.myphoneassistant.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.Category;
import org.crazyit.myphoneassistant.common.Constant;
import org.crazyit.myphoneassistant.common.ImageLoader.ImageLoader;

/**
 * Created by Administrator on 2018/6/17.
 */

public class CategoryAdapter extends BaseQuickAdapter<Category,BaseViewHolder> {

    public CategoryAdapter() {

        super(R.layout.template_category);

    }

    @Override
    protected void convert(BaseViewHolder helper, Category item) {

        helper.setText(R.id.text_name,item.getName());

        ImageLoader.load(Constant.BASE_IMG_URL+item.getIcon(), (ImageView) helper.getView(R.id.img_icon));
    }

}
