package org.crazyit.myphoneassistant.ui.fragment;

import android.app.ProgressDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.crazyit.myphoneassistant.R;

import org.crazyit.myphoneassistant.bean.IndexBean;
import org.crazyit.myphoneassistant.di.component.AppComponent;

import org.crazyit.myphoneassistant.di.component.DaggerRecommendComponent;
import org.crazyit.myphoneassistant.di.module.RecommendModule;
import org.crazyit.myphoneassistant.presenter.RecommendPresenter;
import org.crazyit.myphoneassistant.presenter.contract.AppInfoContract;
import org.crazyit.myphoneassistant.ui.adapter.IndexMultipleAdapter;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * Created by Administrator on 2018/6/7.
 */

public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements AppInfoContract.View {
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    private IndexMultipleAdapter mAdatper;

    @Inject
    ProgressDialog mProgressDialog;
    //使用dragger2的步骤第一需要告诉dragger2我们需要这个对象
    //注意这里不能是private因为私有是没办法进行依赖的
//    @Inject
//    AppInfoContract.Presenter mPresenter;


//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//
////        DaggerRecommendComponent.builder()
////                .recommendModule(new RecommendModule(this)).build().inject(this);
////        mProgressDialog=new ProgressDialog(getActivity());
//        //Dagger2会给我们自动生成生成一个interface RecommendComponent的子类,我们可以看到它是实现了RecommendComponent这个接口的
//        //注入的方法通过build
//        //这个是复杂的方法我们在这里使用简单的额方法
////        DaggerRecommendComponent.builder()
////                .recommendMoudle(new RecommendModule()).build().inject(this);
//
//        //简单的写法是
//        //这个方法需要在onActvityCreate里面去调用比较靠谱
//        //DaggerRecommendComponent.create();
//
//        //其次这个地方需要删掉因为我们已经使用Dragger2了
//        //mPresenter=new RecommendPresenter(this);
//        //Dragger需要我们进行注入注入是不能省的,注入之前需要我们重新编译一下
//        //通过build---rebuild来注入
//
//
//        //特别是这句话经常需要用到因为我们的每一个APPComponent都要去依赖于这个appComponent,
//        // 几乎只要是需要用到注入的地方都需要用到这句话
//        //所以需要我们去写一个方法去返回这个东西的
//        DaggerRecommendComponent.builder().appComponent(((AppApplication)getActivity().getApplication()).getAppComponent())
//                .recommendModule(new RecommendModule(this)).build().inject(this);
//
//        initData();
//        return view;
//    }

//    private void initData() {
//
//        //告诉Presenter我需要去拿data
//        mPresenter.requestDatas();
//
////        //通过retrofit来调用网络服务
////        HttpManager  manager=new HttpManager();
////        ApiService apiService=manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
////
////        apiService.getApps("{'page':0}").enqueue(new Callback<PageBean<AppInfo>>() {
////            @Override
////            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
//////                PageBean<AppInfo> pageBean=response.body();
//////                List<AppInfo> datas=pageBean.getDatas();
////                initRecycleView(datas);
////
////            }
////
////            @Override
////            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
////                Log.d("RecommendFragment","没有获取到数据");
//////                Toast.makeText()
////
////            }
////        });
//    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//    }

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder().appComponent(appComponent)
                .recommendModule(new RecommendModule(this)).build().inject(this);

    }

    @Override
    public void onEmptyViewClick() {
        mPresenter.requestDatas();
    }

    @Override
    public void init() {
        initRecycleView();
//        //告诉Presenter我需要去拿data
        mPresenter.requestDatas();
//        mPresenter.requestPermission();

    }

    private void initRecycleView(){
        //为RecyclerView设置布局管理器
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置分割线
//        recycleView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL));
        //设置动画
        recycleView.setItemAnimator(new DefaultItemAnimator());




    }

    @Override
    public void showResult(IndexBean indexBean) {
        mAdatper=new IndexMultipleAdapter(getActivity());
        mAdatper.setData(indexBean);

        recycleView.setAdapter(mAdatper);
    }
    //    @Override
//    public void showResult(List<AppInfo> datas) {
//        Toast.makeText(getActivity(),"小豆子好",Toast.LENGTH_LONG).show();
//        initRecycleView(datas);
//
//    }

//    @Override
//    public void showNodata() {
//        Toast.makeText(getActivity(),"暂时无数据,请吃完饭再来",Toast.LENGTH_SHORT).show();
//
//    }

//    @Override
//    public void showError(String msg) {
//        Toast.makeText(getActivity(),"服务器开小差了"+msg,Toast.LENGTH_SHORT).show();
//
//    }

    @Override
    public void onRequestPermissonSuccess() {
        mPresenter.requestDatas();

    }

    @Override
    public void onRequestPermissonError() {
        Toast.makeText(getActivity(), "您已经拒绝授权", Toast.LENGTH_SHORT).show();

    }
//    @Override
//    public void onRequestPermissonSuccess() {
//
//        mPresenter.requestDatas();
//    }
//
//    @Override
//    public void onRequestPermissonError() {
//
//        Toast.makeText(getActivity(),"你已拒绝授权",Toast.LENGTH_LONG).show();
//    }
}
