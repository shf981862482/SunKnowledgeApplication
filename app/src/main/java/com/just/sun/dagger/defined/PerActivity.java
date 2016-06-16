
package com.just.sun.dagger.defined;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;


/**
 * Created by walkingMen on 2016/6/8.
 *
 * @PerActivity 是一个自定义的范围注解，作用是允许对象被记录在正确的组件中，当然这些对象的生命周期应该遵循activity的生命周期。
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}

