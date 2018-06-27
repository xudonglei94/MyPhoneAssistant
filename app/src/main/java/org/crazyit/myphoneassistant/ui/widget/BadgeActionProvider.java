package org.crazyit.myphoneassistant.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.crazyit.myphoneassistant.R;

/**
 * Created by Administrator on 2018/6/27.
 */

public class BadgeActionProvider extends ActionProvider {

    private ImageView mIcon;
    private TextView mTxtBadge;
    private View.OnClickListener mOnClickListener;

    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public BadgeActionProvider(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {

        //toolbar当中的源码可以看到abc_action_bar_default_height_material
        int size = getContext().getResources().getDimensionPixelSize(android.support.design.R.dimen.abc_action_bar_default_height_material);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(size,size);

        View  view= LayoutInflater.from(getContext()).inflate(R.layout.menu_badge_provider,null,false);
        view.setLayoutParams(layoutParams);

        this.mIcon = (ImageView) view.findViewById(R.id.img_icon);
        this.mTxtBadge = (TextView) view.findViewById(R.id.txt_badge);
        view.setOnClickListener(new BadgeMenuClickListenter());

        return view;
    }
    public void  setIcon(Drawable drawable){

        this.mIcon.setImageDrawable(drawable);

    }


    public void setIcon(@DrawableRes int res){


        this.mIcon.setImageResource(res);
    }


    // 设置显示的文字。
    public void setText(CharSequence i) {
        showBadge();
        mTxtBadge.setText(i);
    }


    public int  getBadgeNum(){

        try {
            return Integer.parseInt(mTxtBadge.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void hideBadge(){
        mTxtBadge.setVisibility(View.GONE);
    }

    public void showBadge(){
        mTxtBadge.setVisibility(View.VISIBLE);
    }


    public void setOnClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }


    private class BadgeMenuClickListenter implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            if(mOnClickListener !=null){
                mOnClickListener.onClick(v);
            }
        }
    }

}
