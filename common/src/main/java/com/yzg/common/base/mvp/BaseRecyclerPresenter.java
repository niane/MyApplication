package com.yzg.common.base.mvp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.yzg.simplerecyclerview.adapter.SimpleMultiRecyAdapter;

import java.util.List;

/**
 * Created by yzg on 2017/3/27.
 */

public interface BaseRecyclerPresenter<M, V extends BaseRecyclerView> extends BasePresenter<V> {
    SimpleMultiRecyAdapter<M> createAdapter(Context context, List<M> list);
}
