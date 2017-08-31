package com.yzg.myapplication.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.yzg.common.base.mvp.BaseRecyclerActivity;
import com.yzg.myapplication.R;
import com.yzg.myapplication.presenter.contract.ExampleContract;
import com.yzg.myapplication.model.bean.GankPublishBean;
import com.yzg.myapplication.presenter.ExamplePresenterImpl;
import com.yzg.simplerecyclerview.SimpleDecoration;

/**
 * Created by yzg on 2017/3/10.
 */

public class ExampleMvp extends BaseRecyclerActivity<GankPublishBean, ExamplePresenterImpl> implements ExampleContract.ExampleView{

    @Override
    protected void addItemDecoration(RecyclerView recyclerView) {
        recyclerView.addItemDecoration(new SimpleDecoration(this, LinearLayoutManager.VERTICAL, R.drawable.divider_horizontal));
    }

    @Override
    protected void requestData(int pageNO, int pageSize) {
        mPresenter.getGankPublish(pageSize, pageNO);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(ExampleMvp.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public ExamplePresenterImpl newPresenter() {
        return new ExamplePresenterImpl();
    }

}
