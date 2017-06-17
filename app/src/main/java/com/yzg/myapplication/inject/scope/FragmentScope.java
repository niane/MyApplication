package com.yzg.myapplication.inject.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by yzg on 2017/5/26.
 */
@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
