package org.crazyit.myphoneassistant.di.component;

import org.crazyit.myphoneassistant.di.FragmentScope;
import org.crazyit.myphoneassistant.di.module.SearchModule;
import org.crazyit.myphoneassistant.ui.activity.SearchActivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/28.
 */

@FragmentScope
@Component(modules = SearchModule.class,dependencies= AppComponent.class)
public interface SearchComponent {

    void  inject(SearchActivity fragment);
}
