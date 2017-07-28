package com.yzg.myapplication.presenter.contract;

import com.yzg.common.base.mvp.BaseRecyclerPresenter;
import com.yzg.common.base.mvp.BaseRecyclerView;
import com.yzg.myapplication.model.bean.GankPublishBean;

/**
 * Created by yzg on 2017/3/10.
 */

public interface ExampleContract {
    interface ExampleView extends BaseRecyclerView<GankPublishBean> {
        void showMessage(String msg);
    }

    interface ExamplePresenter extends BaseRecyclerPresenter<GankPublishBean, ExampleView> {
        void getGankPublish(int pageSize, int page);
    }
}
