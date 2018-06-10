package org.crazyit.myphoneassistant.di.component;

import org.crazyit.myphoneassistant.di.module.RecommendMoudle;
import org.crazyit.myphoneassistant.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/10.
 */
@Component(modules=RecommendMoudle.class)
//通过Component里面的modules和RecommendMoudle进行关联
public interface RecommendComponent {
    //我们需要写一个方法来关联我们的fragment
    //通过这个inject方法来和我们的fragment进行关联
    void inject(RecommendFragment fragment);
}
