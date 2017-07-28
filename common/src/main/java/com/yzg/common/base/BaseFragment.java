package com.yzg.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by yzg on 2016/11/9.
 * 基类fragment
 */

public abstract class BaseFragment extends Fragment {
    protected final String TAG = getClass().getSimpleName();
    protected View rootView;
    protected Bundle mBundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments();
        initBundle(mBundle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(rootView != null){
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
        rootView = inflater.inflate(getContentLayoutRes(), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onRestoreInstanceState(savedInstanceState);
        onBindViewBefore();

        ButterKnife.bind(this, rootView);

        initViews(rootView);
        initData();
    }

    /**
     * 初始化参数
     *
     * @param bundle
     */
    protected void initBundle(Bundle bundle) {

    }

    protected abstract int getContentLayoutRes();

    protected abstract void initViews(View rootView);


    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnBindViewBefore();
        ButterKnife.unbind(this);
    }

    protected void initData() {
    }

    protected void onBindViewBefore() {
    }

    protected void onUnBindViewBefore(){
    }

    protected void onRestoreInstanceState(Bundle bundle) {
    }
}
