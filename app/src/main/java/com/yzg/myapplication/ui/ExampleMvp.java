package com.yzg.myapplication.ui;

import android.widget.Toast;

import com.yzg.common.base.BaseRecyclerActivity;
import com.yzg.myapplication.app.MyApplication;
import com.yzg.myapplication.presenter.contract.ExampleContract;
import com.yzg.myapplication.model.bean.GankPublishBean;
import com.yzg.myapplication.presenter.ExamplePresenterImpl;

/**
 * Created by yzg on 2017/3/10.
 */

public class ExampleMvp extends BaseRecyclerActivity<GankPublishBean, ExamplePresenterImpl> implements ExampleContract.ExampleView{

    @Override
    protected void initInject() {
        MyApplication.getInstance().getActivityComponent().inject(this);
    }

    @Override
    protected void requestData(int pageNO, int pageSize) {
        mPresenter.getGankPublish(pageSize, pageNO);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(ExampleMvp.this, msg, Toast.LENGTH_SHORT).show();
    }
}
