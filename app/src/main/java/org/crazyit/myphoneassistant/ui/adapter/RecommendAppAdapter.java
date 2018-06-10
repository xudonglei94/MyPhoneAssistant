package org.crazyit.myphoneassistant.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.ui.bean.AppInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/6/8.
 */

public class RecommendAppAdapter extends RecyclerView.Adapter<RecommendAppAdapter.ViewHolder> {


    private List<AppInfo> mDatas;
    private Context mContext;

    private LayoutInflater mLayoutInflater;

    //通过构造方法来传递这个数据
    public RecommendAppAdapter(Context context, List<AppInfo> datas) {
        this.mDatas = datas;
        this.mContext = context;
        mLayoutInflater=LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.template_recommend_app, parent, false));
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppInfo appInfo = mDatas.get(position);

//        holder.mImgIcon

        //服务器的地址这个是绝对地址
        String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
        Picasso.with(mContext).load(baseImgUrl +appInfo.getIcon()).into(holder.imgIcon);

        holder.textTitle.setText(appInfo.getDisplayName());
        holder.textSize.setText((appInfo.getApkSize() / 1024 /1024) +" MB");

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_icon)
        ImageView imgIcon;
        @BindView(R.id.text_title)
        TextView textTitle;
        @BindView(R.id.text_size)
        TextView textSize;
        @BindView(R.id.btn_dl)
        Button btnDl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
