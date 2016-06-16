
package com.just.sun.dagger.component;

import com.common.base.BaseFragmentActivity;
import com.just.sun.dagger.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by walkingMen on 2016/6/8.
 */

@Singleton  // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseFragmentActivity baseActivity);
}

