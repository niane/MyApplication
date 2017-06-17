package com.yzg.myapplication.inject.component;

import com.yzg.myapplication.inject.scope.ActivityScope;
import com.yzg.myapplication.ui.ExampleMvp;

import dagger.Component;

/**
 * Created by yzg on 2017/4/28.
 */

@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(ExampleMvp exampleMvp);
}
