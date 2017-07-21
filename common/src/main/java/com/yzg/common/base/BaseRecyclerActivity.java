package com.yzg.common.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yzg.common.R;
import com.yzg.pulltorefresh.PullToRefreshLayout;
import com.yzg.simplerecyclerview.SimpleRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzg on 2017/3/29.
 *
 * 对简单请求显示列表界面进行封装<br/>
 *
 * 显示列表部分布局必须include R.com.yunya365.yunyapatient.R.layout.frag_list <br/>
 *
 * 对于不同界面使用不同的 Presenter(必须实现{@link BaseRecyclerPresenter})请求数据 <br/>
 *
 */

public abstract class BaseRecyclerActivity<T, P extends BaseRecyclerPresenter> extends BaseActivity<P> implements BaseRecyclerView {
    private SimpleRecyclerView recyclerView;
    private PullToRefreshLayout pullToRefresh;

    protected int pageNO = 1;
    protected int pageSize = 20;

    private List<T> mList = new ArrayList<>();
    private RecyclerView.Adapter adapter;

    protected abstract void requestData(int pageNO, int pageSize);

    @Override
    public int getContentLayoutRes() {
        return R.layout.frag_list;
    }

    @Override
    public void initViews(View rootView) {

        recyclerView = (SimpleRecyclerView) rootView.findViewById(R.id.recycler_view);
        pullToRefresh = (PullToRefreshLayout) rootView.findViewById(R.id.pull_to_refresh);

        adapter = mPresenter.createAdapter(this, mList);
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.setAdapter(adapter);
        recyclerView.setOnLoadListener(new SimpleRecyclerView.OnLoadListener() {
            @Override
            public void onLoadMore() {
                requestData(pageNO, pageSize);
            }

            @Override
            public void onRefresh() {
                pageNO = 1;
                requestData(pageNO, pageSize);
            }
        });

        pullToRefresh.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNO = 1;
                requestData(pageNO, pageSize);
            }
        });

        recyclerView.setStatus(SimpleRecyclerView.STATUS_REFRESHING);
        requestData(pageNO, pageSize);

//        pullToRefresh.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    pullToRefresh.start();
//                }
//            }, 50);
    }

    protected RecyclerView.LayoutManager getLayoutManager(){
        return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void onReturnList(List list) {
        if(pullToRefresh.isRefreshing()){
            pullToRefresh.finish();
        }
        if (pageNO == 1) {
            mList.clear();
        }
        if(list.size() == 0){
            if(pageNO == 1){
                recyclerView.setStatus(SimpleRecyclerView.STATUS_REFRESH_EMPTY);
            }else {
                recyclerView.setStatus(SimpleRecyclerView.STATUS_LOAD_MORE_OVER);
            }
        }else {
            pageNO++;
            recyclerView.setStatus(SimpleRecyclerView.STATUS_DEFAULT);
        }
        mList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadError() {
        if(pullToRefresh.isRefreshing()){
            pullToRefresh.finish();
        }

        if(pageNO > 1){
            recyclerView.setStatus(SimpleRecyclerView.STATUS_LOAD_MORE_ERROR);
        }else {
            mList.clear();
            recyclerView.setStatus(SimpleRecyclerView.STATUS_REFRESH_ERROR);
            adapter.notifyDataSetChanged();
        }
    }
}
