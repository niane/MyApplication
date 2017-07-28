package com.yzg.common.base.mvp;

import com.yzg.common.app.YException;

import java.util.List;

/**
 * Created by yzg on 2017/3/27.
 */

public interface BaseRecyclerView<M> extends BaseView {
    void onReturnList(List<M> list, int pageNo);
    void onLoadError(YException e, int pageNo);
}
