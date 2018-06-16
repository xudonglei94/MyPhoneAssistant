package org.crazyit.myphoneassistant.di.component;

import org.crazyit.myphoneassistant.di.FragmentScope;
import org.crazyit.myphoneassistant.di.module.AppInfoModule;
import org.crazyit.myphoneassistant.di.module.LoginModule;
import org.crazyit.myphoneassistant.ui.activity.LoginActivity;
import org.crazyit.myphoneassistant.ui.fragment.GamesFragment;
import org.crazyit.myphoneassistant.ui.fragment.TopListFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/14.
 */
@FragmentScope
@Component(modules = LoginModule.class,dependencies = AppComponent.class)
public interface LoginComponent {

    void inject(LoginActivity activity);
}
