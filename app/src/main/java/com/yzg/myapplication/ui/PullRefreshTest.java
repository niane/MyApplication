package com.yzg.myapplication.ui;

import android.view.View;

import com.yzg.common.base.BaseActivity;
import com.yzg.myapplication.R;
import com.yzg.pulltorefresh.PullToRefreshLayout;

import butterknife.Bind;

/**
 * Created by yzg on 2017/2/13.
 */

public class PullRefreshTest extends BaseActivity {
    @Bind(R.id.pull_refresh)
    PullToRefreshLayout pullRefresh;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.act_pull_refresh;
    }

    @Override
    protected void initViews(View rootView) {
        pullRefresh.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefresh.finish();
                    }
                }, 5000);
            }
        });
    }

}
