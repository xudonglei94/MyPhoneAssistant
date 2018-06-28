package org.crazyit.myphoneassistant.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.crazyit.myphoneassistant.R;

/**
 * Created by Administrator on 2018/6/28.
 */

public class SearchHistoryAdatper extends BaseQuickAdapter<String,BaseViewHolder> {
    public SearchHistoryAdatper() {
        super(R.layout.template_search_history);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.btn,item);

        helper.addOnClickListener(R.id.btn);

    }
}
