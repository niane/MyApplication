package com.yzg.myapplication.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.yzg.common.base.mvp.BaseRecyclerPresenter;
import com.yzg.common.base.mvp.BaseRecyclerView;
import com.yzg.myapplication.R;
import com.yzg.simplerecyclerview.adapter.RecyViewHolder;
import com.yzg.simplerecyclerview.adapter.SimpleRecyAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yzg on 2017/5/26.
 */

public class FragmentPresenterImpl implements BaseRecyclerPresenter<String, BaseRecyclerView> {

    public FragmentPresenterImpl(){

    }

    @Override
    public void attachView(BaseRecyclerView view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public SimpleRecyAdapter<String> createAdapter(Context context, List list) {
        return new SimpleRecyAdapter<String>(context, R.layout.listview_item, list) {
            @Override
            protected void convert(RecyViewHolder viewHolder, String string, int position) {
                viewHolder.setText(R.id.text, string);
            }
        };
    }
}
