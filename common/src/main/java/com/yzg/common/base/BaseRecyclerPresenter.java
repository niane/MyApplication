package com.yzg.common.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by yzg on 2017/3/27.
 */

public interface BaseRecyclerPresenter<T, V extends BaseRecyclerView> extends BasePresenter<V> {
    RecyclerView.Adapter createAdapter(Context context, List<T> list);
}
