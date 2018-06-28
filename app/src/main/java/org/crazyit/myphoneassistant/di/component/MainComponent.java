package org.crazyit.myphoneassistant.di.component;

import org.crazyit.myphoneassistant.di.FragmentScope;
import org.crazyit.myphoneassistant.di.module.MainModule;
import org.crazyit.myphoneassistant.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/28.
 */

@FragmentScope
@Component(modules = MainModule.class,dependencies= AppComponent.class)
public interface MainComponent {

    void  inject(MainActivity activity);
}

