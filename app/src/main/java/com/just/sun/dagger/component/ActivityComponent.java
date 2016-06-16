
package com.just.sun.dagger.component;

import android.app.Activity;

import com.common.base.BaseFragmentActivity;
import com.just.sun.dagger.activity.FruitActivity;
import com.just.sun.dagger.defined.PerActivity;
import com.just.sun.dagger.module.ActivityModule;

import dagger.Component;


/**
 * Created by walkingMen on 2016/6/8.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    //Exposed to sub-graphs.
    void inject(BaseFragmentActivity baseFragmentActivity);//inject方法需要一个消耗依赖的类型对象作为参数。
    Activity activity();
}

