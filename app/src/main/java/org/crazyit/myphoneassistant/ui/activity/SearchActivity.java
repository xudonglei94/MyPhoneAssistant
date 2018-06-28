package org.crazyit.myphoneassistant.ui.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mikepenz.iconics.Iconics;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import org.crazyit.myphoneassistant.R;
import org.crazyit.myphoneassistant.bean.SearchResult;
import org.crazyit.myphoneassistant.common.rx.RxSchedulers;
import org.crazyit.myphoneassistant.di.component.AppComponent;
import org.crazyit.myphoneassistant.di.component.DaggerSearchComponent;
import org.crazyit.myphoneassistant.di.module.SearchModule;
import org.crazyit.myphoneassistant.presenter.SearchPresenter;
import org.crazyit.myphoneassistant.presenter.contract.SearchContract;
import org.crazyit.myphoneassistant.ui.adapter.AppInfoAdapter;
import org.crazyit.myphoneassistant.ui.adapter.SearchHistoryAdatper;
import org.crazyit.myphoneassistant.ui.adapter.SuggestionAdapter;
import org.crazyit.myphoneassistant.ui.widget.DividerItemDecoration;
import org.crazyit.myphoneassistant.ui.widget.SpaceItemDecoration2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import zlc.season.rxdownload2.RxDownload;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.SearchView  {

    @BindView(R.id.searchTextView)
    EditText mSearchTextView;
    @BindView(R.id.action_clear_btn)
    ImageView mActionClearBtn;

    @BindView(R.id.search_bar)
    RelativeLayout mSearchBar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.btn_clear)
    ImageView mBtnClear;

    @BindView(R.id.recycler_view_history)
    RecyclerView mRecyclerViewHistory;

    @BindView(R.id.layout_history)
    LinearLayout mLayoutHistory;

    @BindView(R.id.recycler_view_suggestion)
    RecyclerView mRecyclerViewSuggestion;

    @BindView(R.id.recycler_view_result)
    RecyclerView mRecyclerViewResult;

    @BindView(R.id.activity_search)
    LinearLayout mActivitySearch;


    private SuggestionAdapter mSuggestionAdapter;
    private AppInfoAdapter mAppInfoAdapter;
    private SearchHistoryAdatper mHistoryAdatper;

    @Inject
    RxDownload rxDownload;

    private Disposable disposable;


    @Override
    public int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSearchComponent.builder().appComponent(appComponent)
                .searchModule(new SearchModule(this))
                .build()
                .inject(this);

    }

    @Override
    public void init() {
        mPresenter.showHistory();
        initView();

        setupSearchView();
        setupSuggestionRecyclerView();
        initSearchResultRecycleView();

    }
    private void initView() {

        mToolbar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        mActionClearBtn.setImageDrawable(new IconicsDrawable(this, Ionicons.Icon.ion_ios_close_empty)
                .color(ContextCompat.getColor(this,R.color.white)).sizeDp(16));


        mBtnClear.setImageDrawable(new IconicsDrawable(this, Ionicons.Icon.ion_ios_trash_outline)
                .color(ContextCompat.getColor(this,R.color.md_grey_600)).sizeDp(16));

        RxView.clicks(mBtnClear).subscribe(new Consumer<Object>() {

            @Override
            public void accept(@NonNull Object o) throws Exception {

            }
        });
    }
    private void setupSuggestionRecyclerView() {

        mSuggestionAdapter = new SuggestionAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewSuggestion.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);

        mRecyclerViewSuggestion.addItemDecoration(itemDecoration);

        mRecyclerViewSuggestion.setAdapter(mSuggestionAdapter);


        mRecyclerViewSuggestion.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                search(mSuggestionAdapter.getItem(position));
            }
        });

    }

    private void initSearchResultRecycleView(){
        mAppInfoAdapter = AppInfoAdapter.builder().showBrief(false).showCategoryName(true).rxDownload(rxDownload).build();

        LinearLayoutManager layoutManager =  new LinearLayoutManager(this);
        mRecyclerViewResult.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST);
        mRecyclerViewResult.addItemDecoration(itemDecoration);

        mRecyclerViewResult.setAdapter(mAppInfoAdapter);

        mRecyclerViewResult.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }
    private void initHistoryRecycleView(List<String> list) {


        mHistoryAdatper = new SearchHistoryAdatper();
        mHistoryAdatper.addData(list);


        RecyclerView.LayoutManager lm = new GridLayoutManager(this,5);
        SpaceItemDecoration2 itemd = new SpaceItemDecoration2(10);
        mRecyclerViewHistory.addItemDecoration(itemd);

        mRecyclerViewHistory.setLayoutManager(lm);
        mRecyclerViewHistory.setAdapter(mHistoryAdatper);

        mRecyclerViewHistory.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                search(mHistoryAdatper.getItem(position));
            }
        });

    }

    private void setupSearchView() {

        mActionClearBtn.setImageDrawable(new IconicsDrawable(this, Ionicons.Icon.ion_ios_close_empty)
             .color(ContextCompat.getColor(this,R.color.white)).sizeDp(16));

        RxView.clicks(mActionClearBtn).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

                mSearchTextView.setText("");
                mLayoutHistory.setVisibility(View.VISIBLE);
                mRecyclerViewSuggestion.setVisibility(View.GONE);
                mRecyclerViewResult.setVisibility(View.GONE);
            }
        });
        RxTextView.editorActions(mSearchTextView).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                search(mSearchTextView.getText().toString().trim());
            }
        });

        disposable=RxTextView.textChanges(mSearchTextView)
                .debounce(400, TimeUnit.MILLISECONDS)
                .compose(RxSchedulers.<CharSequence>io_main())
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(CharSequence charSequence) throws Exception {
                        return charSequence.toString().trim().length()>0;
                    }
                })
                .subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {

                Log.d("SearchActivity",charSequence.toString());

                if (charSequence.length()>0){
                    mActionClearBtn.setVisibility(View.VISIBLE);
                }
                else{
                    mActionClearBtn.setVisibility(View.GONE);
                }
                mPresenter.getSuggestions(charSequence.toString().trim());
            }
        });
    }
    private  void search(String keyword){
//        mSearchTextView.setText(keyword);
//        if (disposable!=null){
//            disposable.dispose();
//        }

        mPresenter.search(keyword);

    }

    @Override
    public void showSearchHistory(List<String> list) {
        initHistoryRecycleView(list);
        mRecyclerViewSuggestion.setVisibility(View.GONE);
        mLayoutHistory.setVisibility(View.VISIBLE);
        mRecyclerViewResult.setVisibility(View.GONE);

    }

    @Override
    public void showSuggestions(List<String> list) {
        mSuggestionAdapter.setNewData(list);
        mRecyclerViewSuggestion.setVisibility(View.VISIBLE);

        mLayoutHistory.setVisibility(View.GONE);
        mRecyclerViewResult.setVisibility(View.GONE);

    }

    @Override
    public void showSearchResult(SearchResult result) {
        //setNewData将原来的数据清空掉然后重新添加数据
        mAppInfoAdapter.setNewData(result.getListApp());
        mRecyclerViewSuggestion.setVisibility(View.GONE);
        mLayoutHistory.setVisibility(View.GONE);
        mRecyclerViewResult.setVisibility(View.VISIBLE);

    }
}
