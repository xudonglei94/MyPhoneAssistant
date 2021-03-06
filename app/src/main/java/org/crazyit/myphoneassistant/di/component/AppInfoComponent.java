package org.crazyit.myphoneassistant.di.component;

import org.crazyit.myphoneassistant.di.FragmentScope;
import org.crazyit.myphoneassistant.di.module.AppInfoModule;
import org.crazyit.myphoneassistant.ui.fragment.BaseAppInfoFragment;
import org.crazyit.myphoneassistant.ui.fragment.CategoryAppFragment;
import org.crazyit.myphoneassistant.ui.fragment.GamesFragment;
import org.crazyit.myphoneassistant.ui.fragment.TopListFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/14.
 */
@FragmentScope
@Component(modules=AppInfoModule.class,dependencies = AppComponent.class)
public interface AppInfoComponent {
    //我们需要写一个方法来关联我们的fragment
    //通过这个inject方法来和我们的fragment进行关联
    void injectTopListFragment(BaseAppInfoFragment fragment) ;
//    void injectGamesFragment(GamesFragment fragment);
//    void injectCategoryAppFragment(CategoryAppFragment fragment);


}
