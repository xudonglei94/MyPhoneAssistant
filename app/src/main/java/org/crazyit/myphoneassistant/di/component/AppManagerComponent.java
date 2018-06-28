package org.crazyit.myphoneassistant.di.component;

import org.crazyit.myphoneassistant.di.FragmentScope;
import org.crazyit.myphoneassistant.di.module.AppManagerModule;
import org.crazyit.myphoneassistant.ui.fragment.AppManagerFragment;
import org.crazyit.myphoneassistant.ui.fragment.DownloadedFragment;
import org.crazyit.myphoneassistant.ui.fragment.DownloadingFragment;
import org.crazyit.myphoneassistant.ui.fragment.InstalledAppAppFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/20.
 */

@FragmentScope
@Component(modules = AppManagerModule.class,dependencies = AppComponent.class)
public interface AppManagerComponent {

    void inject(AppManagerFragment fragment);
//    void injectDownloaded(DownloadedFragment fragment);
//    void injectInstalled(InstalledAppAppFragment fragment);
}
