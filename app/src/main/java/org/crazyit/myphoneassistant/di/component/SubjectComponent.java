package org.crazyit.myphoneassistant.di.component;

import org.crazyit.myphoneassistant.di.FragmentScope;
import org.crazyit.myphoneassistant.di.module.SubjectModule;
import org.crazyit.myphoneassistant.ui.fragment.BaseSubjectFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/28.
 */

@FragmentScope
@Component(modules = SubjectModule.class,dependencies= AppComponent.class)
public interface SubjectComponent {

    void  inject(BaseSubjectFragment fragment);
}

