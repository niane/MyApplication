package com.yzg.common.base;

import java.util.List;

/**
 * Created by yzg on 2017/3/27.
 */

public interface BaseRecyclerView extends BaseView {
    void onReturnList(List list);
    void onLoadError();
}
