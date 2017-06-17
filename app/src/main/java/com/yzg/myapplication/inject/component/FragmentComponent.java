package com.yzg.myapplication.inject.component;

import com.yzg.myapplication.fragment.RecyclerFragmentTest;
import com.yzg.myapplication.inject.scope.FragmentScope;

import dagger.Component;

/**
 * Created by yzg on 2017/5/26.
 */

@FragmentScope
@Component(dependencies = AppComponent.class)
public interface FragmentComponent {
    void inject(RecyclerFragmentTest recyclerFragmentTest);
}
