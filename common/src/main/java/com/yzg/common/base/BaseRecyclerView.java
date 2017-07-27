package com.yzg.common.base;

import com.yzg.common.app.YException;

import java.util.List;

/**
 * Created by yzg on 2017/3/27.
 */

public interface BaseRecyclerView<T> extends BaseView {
    void onReturnList(List<T> list);
    void onLoadError(YException e);
}
