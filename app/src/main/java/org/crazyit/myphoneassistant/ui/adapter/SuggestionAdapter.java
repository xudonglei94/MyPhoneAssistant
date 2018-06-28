package org.crazyit.myphoneassistant.ui.adapter;

import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import org.crazyit.myphoneassistant.R;

/**
 * Created by Administrator on 2018/6/28.
 */

public class SuggestionAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public SuggestionAdapter() {
        super(R.layout.suggest_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {


        ImageView icon = helper.getView(R.id.icon_suggestion);
        icon.setImageDrawable(new IconicsDrawable(mContext, Ionicons.Icon.ion_ios_search)
                .color(ContextCompat.getColor(mContext,R.color.white)).sizeDp(16));

        helper.setText(R.id.txt_suggestion,item);



    }
}
