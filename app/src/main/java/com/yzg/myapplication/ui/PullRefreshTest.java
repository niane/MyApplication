package com.yzg.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yzg.myapplication.R;
import com.yzg.pulltorefresh.PullToRefreshLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yzg on 2017/2/13.
 */

public class PullRefreshTest extends AppCompatActivity {
    @Bind(R.id.pull_refresh)
    PullToRefreshLayout pullRefresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pull_refresh);
        ButterKnife.bind(this);

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

//        pullRefresh.start();
    }
}
