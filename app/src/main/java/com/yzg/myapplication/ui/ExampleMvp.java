package com.yzg.myapplication.ui;

import android.view.View;
import android.widget.Toast;

import com.yzg.common.base.mvp.BaseRecyclerActivity;
import com.yzg.myapplication.app.MyApplication;
import com.yzg.myapplication.presenter.contract.ExampleContract;
import com.yzg.myapplication.model.bean.GankPublishBean;
import com.yzg.myapplication.presenter.ExamplePresenterImpl;

import java.util.List;

/**
 * Created by yzg on 2017/3/10.
 */

public class ExampleMvp extends BaseRecyclerActivity<GankPublishBean, ExamplePresenterImpl> implements ExampleContract.ExampleView{

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

    @Override
    public void onItemClick(View view, int i) {
        super.onItemClick(view, i);
    }
}
