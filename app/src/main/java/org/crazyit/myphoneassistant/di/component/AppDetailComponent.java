package org.crazyit.myphoneassistant.di.component;

import org.crazyit.myphoneassistant.di.FragmentScope;
import org.crazyit.myphoneassistant.di.module.AppDetailModule;
import org.crazyit.myphoneassistant.di.module.AppInfoModule;
import org.crazyit.myphoneassistant.ui.activity.AppDetailActivity;
import org.crazyit.myphoneassistant.ui.fragment.AppDetailFragment;
import org.crazyit.myphoneassistant.ui.fragment.CategoryAppFragment;
import org.crazyit.myphoneassistant.ui.fragment.GamesFragment;
import org.crazyit.myphoneassistant.ui.fragment.TopListFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/14.
 */
@FragmentScope
@Component(modules=AppDetailModule.class,dependencies = AppComponent.class)
public interface AppDetailComponent {

    void inject(AppDetailFragment fragment);

}
