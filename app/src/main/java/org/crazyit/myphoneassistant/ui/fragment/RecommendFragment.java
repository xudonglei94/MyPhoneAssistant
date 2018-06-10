package org.crazyit.myphoneassistant.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.di.DaggerRecommendComponent;
import org.crazyit.myphoneassistant.di.module.RecommendMoudle;
import org.crazyit.myphoneassistant.ui.adapter.RecommendAppAdapter;
import org.crazyit.myphoneassistant.bean.AppInfo;
import org.crazyit.myphoneassistant.presenter.contract.RecommendContract;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2018/6/7.
 */

public class RecommendFragment extends Fragment implements RecommendContract.View {
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    private RecommendAppAdapter mAdatper;

    @Inject
    ProgressDialog mProgressDialog;
    //使用dragger2的步骤第一需要告诉dragger2我们需要这个对象
    //注意这里不能是private因为私有是没办法进行依赖的
    @Inject
    RecommendContract.Presenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);


        ButterKnife.bind(this, view);

        DaggerRecommendComponent.builder()
                .recommendMoudle(new RecommendMoudle(this)).build().inject(this);
//        mProgressDialog=new ProgressDialog(getActivity());
        //Dagger2会给我们自动生成生成一个interface RecommendComponent的子类,我们可以看到它是实现了RecommendComponent这个接口的
        //注入的方法通过build
        //这个是复杂的方法我们在这里使用简单的额方法
//        DaggerRecommendComponent.builder()
//                .recommendMoudle(new RecommendMoudle()).build().inject(this);

        //简单的写法是
        //这个方法需要在onActvityCreate里面去调用比较靠谱
        //DaggerRecommendComponent.create();

        //其次这个地方需要删掉因为我们已经使用Dragger2了
        //mPresenter=new RecommendPresenter(this);
        //Dragger需要我们进行注入注入是不能省的,注入之前需要我们重新编译一下
        //通过build---rebuild来注入

        initData();
        return view;
    }

    private void initData() {

        //告诉Presenter我需要去拿data
        mPresenter.requestDatas();

//        //通过retrofit来调用网络服务
//        HttpManager  manager=new HttpManager();
//        ApiService apiService=manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
//
//        apiService.getApps("{'page':0}").enqueue(new Callback<PageBean<AppInfo>>() {
//            @Override
//            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
////                PageBean<AppInfo> pageBean=response.body();
////                List<AppInfo> datas=pageBean.getDatas();
//                initRecycleView(datas);
//
//            }
//
//            @Override
//            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
//                Log.d("RecommendFragment","没有获取到数据");
////                Toast.makeText()
//
//            }
//        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initRecycleView(List<AppInfo> datas){
        //为RecyclerView设置布局管理器
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL));
        //设置动画
        recycleView.setItemAnimator(new DefaultItemAnimator());

        mAdatper=new RecommendAppAdapter(getActivity(),datas);
        recycleView.setAdapter(mAdatper);

    }


    @Override
    public void showLoading() {
        mProgressDialog.show();

    }

    @Override
    public void dimissLoading() {
        if (mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void showResult(List<AppInfo> datas) {
        initRecycleView(datas);

    }

    @Override
    public void showNodata() {
        Toast.makeText(getActivity(),"暂时无数据,请吃完饭再来",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(),"服务器开小差了"+msg,Toast.LENGTH_SHORT).show();

    }
}
