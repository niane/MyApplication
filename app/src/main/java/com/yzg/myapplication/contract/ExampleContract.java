package com.yzg.myapplication.contract;

import com.yzg.common.base.BaseRecyclerPresenter;
import com.yzg.common.base.BaseRecyclerView;
import com.yzg.myapplication.model.bean.GankPublishBean;

/**
 * Created by yzg on 2017/3/10.
 */

public interface ExampleContract {
    interface ExampleView extends BaseRecyclerView {
        void showToast(String toastStr);
    }

    interface ExamplePresenter extends BaseRecyclerPresenter<GankPublishBean, ExampleView> {
        void getGankPublish(int pageSize, int page);
    }
}
