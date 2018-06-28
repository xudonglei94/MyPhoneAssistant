package org.crazyit.myphoneassistant.presenter.contract;

import org.crazyit.myphoneassistant.bean.BaseBean;
import org.crazyit.myphoneassistant.bean.SearchResult;
import org.crazyit.myphoneassistant.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/6/28.
 */

public class SearchContract {


    public  interface   SearchView extends BaseView {


        void showSearchHistory(List<String> list);
        void showSuggestions(List<String> list);
        void showSearchResult(SearchResult result);

    }





    public interface ISearchModel{

        Observable<BaseBean<List<String>>> getSuggestion(String keyword);

        Observable<BaseBean<SearchResult>> search(String keyword);

    }
}
