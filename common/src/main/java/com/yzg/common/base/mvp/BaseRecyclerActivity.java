package com.yzg.common.base.mvp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yzg.common.R;
import com.yzg.common.app.ExceptionHandler;
import com.yzg.common.app.YException;
import com.yzg.pulltorefresh.PullToRefreshLayout;
import com.yzg.simplerecyclerview.SimpleRecyclerView;
import com.yzg.simplerecyclerview.adapter.SimpleMultiRecyAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.yzg.simplerecyclerview.SimpleRecyclerView.STATUS_LOADING_MORE;
import static com.yzg.simplerecyclerview.SimpleRecyclerView.STATUS_REFRESHING;

/**
 * Created by yzg on 2017/3/29.
 *
 * 对简单请求显示列表界面进行封装<br/>
 *
 * 显示列表部分布局必须include com.yzg.common.R.layout.base_recycler_fragment <br/>
 *
 * 对于不同界面使用不同的 Presenter(必须实现{@link BaseRecyclerPresenter})请求数据 <br/>
 *
 */

public abstract class BaseRecyclerActivity<M, P extends BaseRecyclerPresenter> extends BaseMvpActivity<P> implements BaseRecyclerView<M>, SimpleMultiRecyAdapter.OnItemClickListener {
    private SimpleRecyclerView recyclerView;
    private PullToRefreshLayout pullToRefresh;

    protected int firstPageNO = 1;
    protected int currentPageNO = firstPageNO;
    protected int pageSize = 20;


    protected List<M> mList = new ArrayList<>();
    protected SimpleMultiRecyAdapter adapter;

    protected abstract void requestData(int pageNO, int pageSize);

    @Override
    public int getContentLayoutRes() {
        return R.layout.base_recycler_fragment;
    }

    @Override
    public void initViews(View rootView) {

        recyclerView = (SimpleRecyclerView) rootView.findViewById(R.id.recycler_view);
        pullToRefresh = (PullToRefreshLayout) rootView.findViewById(R.id.pull_to_refresh);

        adapter = mPresenter.createAdapter(this, mList);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.setAdapter(adapter);
        recyclerView.setOnLoadListener(new SimpleRecyclerView.OnLoadListener() {
            @Override
            public void onLoadMore() {
                setLoadingMoreStatus();
                requestData(++currentPageNO, pageSize);
            }

            @Override
            public void onRefresh() {
                mList.clear();
                setRefreshStatus();
                currentPageNO = firstPageNO;
                requestData(currentPageNO, pageSize);
            }
        });

        pullToRefresh.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPageNO = firstPageNO;
                requestData(currentPageNO, pageSize);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        setRefreshStatus();
        currentPageNO = firstPageNO;
        requestData(currentPageNO, pageSize);
    }

    @Override
    public void onItemClick(View view, int i) {

    }

    protected RecyclerView.LayoutManager getLayoutManager(){
        return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void onReturnList(List<M> list, int pageNO) {
        if (pageNO == firstPageNO) {
            mList.clear();
            if(pullToRefresh.isRefreshing()){
                pullToRefresh.finish();
            }
        }

        mList.addAll(list);

        if(list.size() == 0){
            if(pageNO == firstPageNO){
                setEmptyStatus();
            }else {
                currentPageNO--;
                setNomoreStatus();
            }
        }else {
            recyclerView.setStatus(SimpleRecyclerView.STATUS_DEFAULT);
        }
    }

    @Override
    public void onLoadError(YException e, int pageNO) {
        if(pageNO > firstPageNO){
            currentPageNO--;
            setLoadingMoreErrorStatus(e);
        }else {
            if(pullToRefresh.isRefreshing()){
                pullToRefresh.finish();
            }

            if(e.getCode() == ExceptionHandler.NETWORK_ACCESS){
                setNetWorkErrorStatus(e);
            }else {
                setRefreshErrorStatus(e);
            }
        }
    }

    protected void setEmptyStatus(){
        recyclerView.setStatus(SimpleRecyclerView.STATUS_REFRESH_EMPTY);
    }

    protected void setNomoreStatus(){
        recyclerView.setStatus(SimpleRecyclerView.STATUS_LOAD_MORE_OVER);
    }

    protected void setLoadingMoreStatus(){
        recyclerView.setStatus(STATUS_LOADING_MORE);
    }

    protected void setRefreshStatus(){
        recyclerView.setStatus(STATUS_REFRESHING);
    }

    protected void setLoadingMoreErrorStatus(YException e){
        recyclerView.setStatus(SimpleRecyclerView.STATUS_LOAD_MORE_ERROR, e.getMessage());
    }

    protected void setRefreshErrorStatus(YException e){
        recyclerView.setStatus(SimpleRecyclerView.STATUS_REFRESH_ERROR, e.getMessage());
    }

    protected void setNetWorkErrorStatus(YException e){
        recyclerView.setStatus(SimpleRecyclerView.STATUS_NETWORK_ERROR, e.getMessage());
    }
}
