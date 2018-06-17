package org.crazyit.myphoneassistant.di.component;

import org.crazyit.myphoneassistant.di.FragmentScope;
import org.crazyit.myphoneassistant.di.module.CategoryModule;
import org.crazyit.myphoneassistant.di.module.LoginModule;
import org.crazyit.myphoneassistant.ui.activity.LoginActivity;
import org.crazyit.myphoneassistant.ui.fragment.CategoryFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/14.
 */
@FragmentScope
@Component(modules = CategoryModule.class,dependencies = AppComponent.class)
public interface CategoryComponent {

    void inject(CategoryFragment fragment);
}
