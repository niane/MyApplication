package com.yzg.myapplication.ui;

import android.view.View;
import android.widget.Toast;

import com.yzg.common.base.mvp.BaseRecyclerActivity;
import com.yzg.myapplication.R;
import com.yzg.myapplication.presenter.contract.ExampleContract;
import com.yzg.myapplication.model.bean.GankPublishBean;
import com.yzg.myapplication.presenter.ExamplePresenterImpl;

/**
 * Created by yzg on 2017/3/10.
 */

public class ExampleMvp extends BaseRecyclerActivity<GankPublishBean, ExamplePresenterImpl> implements ExampleContract.ExampleView{

    @Override
    public int getContentLayoutRes() {
        return R.layout.frag_list_1;
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
