package com.yzg.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by yzg on 2016/11/9.
 * 基类fragment
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    protected final String TAG = getClass().getSimpleName();
    protected View rootView;

    @Inject
    protected P mPresenter;
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
        rootView = inflater.inflate(getContentLayoutRes(), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, rootView);

        initInject();
        initView(rootView);

        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 初始化参数
     *
     * @param bundle
     */
    protected void initBundle(Bundle bundle) {

    }

    protected abstract int getContentLayoutRes();

    protected abstract void initView(View rootView);

    protected abstract void initInject();

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        ButterKnife.unbind(this);
    }
}
