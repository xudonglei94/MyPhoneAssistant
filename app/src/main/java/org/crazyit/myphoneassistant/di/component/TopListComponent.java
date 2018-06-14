package org.crazyit.myphoneassistant.di.component;

import org.crazyit.myphoneassistant.di.FragmentScope;
import org.crazyit.myphoneassistant.di.module.RecommendModule;
import org.crazyit.myphoneassistant.di.module.TopListModule;
import org.crazyit.myphoneassistant.presenter.contract.AppInfoContract;
import org.crazyit.myphoneassistant.ui.fragment.RecommendFragment;
import org.crazyit.myphoneassistant.ui.fragment.TopListFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/14.
 */
@FragmentScope
@Component(modules=TopListModule.class,dependencies = AppComponent.class)
public interface TopListComponent {
    //我们需要写一个方法来关联我们的fragment
    //通过这个inject方法来和我们的fragment进行关联
    void inject(TopListFragment fragment) ;


}
