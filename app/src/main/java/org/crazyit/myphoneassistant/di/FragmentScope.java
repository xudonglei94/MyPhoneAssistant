package org.crazyit.myphoneassistant.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2018/6/10.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface FragmentScope {
}
