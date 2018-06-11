package org.crazyit.myphoneassistant.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.crazyit.myphoneassistant.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/6/11.
 */

public class GuideFragment extends Fragment {

    public static final String IMG_ID = "IMG_ID";
    public static final String COLOR_ID = "COLOR_ID";
    public static final String TEXT_ID = "TEXT_ID";
    @BindView(R.id.imgView)
    ImageView imgView;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    Unbinder unbinder;
    private View mView;


    public static GuideFragment newInstance(int imgResId, int bgColorResId, int textResId) {
        GuideFragment fragment = new GuideFragment();

        Bundle bundle = new Bundle();

        bundle.putInt(IMG_ID, imgResId);
        bundle.putInt(COLOR_ID, bgColorResId);
        bundle.putInt(TEXT_ID, textResId);
        fragment.setArguments(bundle);

        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_guide, container, false);

        unbinder = ButterKnife.bind(this, mView);
        initData();
        return mView;
    }

    private void initData() {
        Bundle args=getArguments();
        int colorId=args.getInt(COLOR_ID);
        int imgId=args.getInt(IMG_ID);
        int textId=args.getInt(TEXT_ID);

        //如果只是把这个colorId传过来发现没有什么用,将getResources().getColor(colorId)传过来才行
        rootView.setBackgroundColor(getResources().getColor(colorId));
        imgView.setImageResource(imgId);
        text.setText(textId);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
