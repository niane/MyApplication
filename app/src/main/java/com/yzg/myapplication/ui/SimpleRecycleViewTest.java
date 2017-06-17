package com.yzg.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.yzg.myapplication.R;
import com.yzg.pulltorefresh.PullToRefreshLayout;
import com.yzg.simplerecyclerview.SimpleRecyclerView;
import com.yzg.simplerecyclerview.adapter.RecyViewHolder;
import com.yzg.simplerecyclerview.adapter.SimpleRecyAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yzg on 2017/3/3.
 */

public class SimpleRecycleViewTest extends AppCompatActivity {
    private static final String Tag = SimpleRecycleViewTest.class.getSimpleName();

    @Bind(R.id.comm_recyView)
    SimpleRecyclerView commRecyView;
    @Bind(R.id.pull_refresh)
    PullToRefreshLayout pullRefresh;

    private List<String> mList = new ArrayList<>();
    private RecyclerView.Adapter adapter;

    private static final int OK = 0;
    private static final int ERROR = 1;
    private static final int EMPRTY = 2;
    private static final int OVER = 3;
    private static final int OK_1 = 4;
    private static final int OK_2 = 5;
    private static final int OVER_1 = 6;

    private int pageNO = 0;
    private final int paseSize = 20;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_comm_recyview);
        ButterKnife.bind(this);

        adapter = new SimpleRecyAdapter<String>(this, R.layout.listview_item, mList) {
            @Override
            protected void convert(RecyViewHolder holder, String string, int position) {
                holder.setText(R.id.text, string);
            }
        };

        commRecyView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        commRecyView.setAdapter(adapter);
        commRecyView.setOnLoadListener(new SimpleRecyclerView.OnLoadListener() {
            @Override
            public void onLoadMore() {
                requestDataList();
            }

            @Override
            public void onRefresh() {
                pullRefresh.start();
            }
        });

        pullRefresh.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNO = 0;
                requestDataList();
            }
        });

        pullRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                pullRefresh.start();
            }
        }, 50);

    }

    private void requestDataList() {
        Log.e(Tag, "Request datas........");
        commRecyView.postDelayed(new Runnable() {
            @Override
            public void run() {
                int state = new Random().nextInt(100)%7;
                int size = 0;
                switch (state){
                    case OK:
                    case OK_1:
                    case OK_2:
                        if(pageNO == 0){
                            mList.clear();
                        }

                        size = mList.size();
                        for (int i = size; i < size + paseSize; i++) {
                            mList.add("Item " + i);
                        }
                        commRecyView.setStatus(SimpleRecyclerView.STATUS_DEFAULT);
                        commRecyView.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SimpleRecycleViewTest.this, "成功请求了" + paseSize + "条数据", Toast.LENGTH_SHORT).show();
                            }
                        });
                        pageNO++;
                        break;
                    case EMPRTY:
                        if(pageNO == 0){
                            mList.clear();
                            commRecyView.setStatus(SimpleRecyclerView.STATUS_REFRESH_EMPTY);
                        }else {
                            commRecyView.setStatus(SimpleRecyclerView.STATUS_LOAD_MORE_OVER);
                        }
                        break;
                    case ERROR:
                        if(pageNO == 0){
                            if(pullRefresh.isRefreshing()){
                                pullRefresh.finish();
                            }

                            mList.clear();
                            commRecyView.setStatus(SimpleRecyclerView.STATUS_REFRESH_ERROR);
                        }else {
                            commRecyView.setStatus(SimpleRecyclerView.STATUS_LOAD_MORE_ERROR);
                        }
                        break;
                    case OVER:
                    case OVER_1:
                        if(pageNO == 0){
                            mList.clear();
                        }
                        size = mList.size();
                        final int num = new Random().nextInt(paseSize - 5) + 3;
                        for (int i = size; i < size + num; i++) {
                            mList.add("Item " + i);
                        }
                        commRecyView.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SimpleRecycleViewTest.this, "成功请求了" + num + "条数据", Toast.LENGTH_SHORT).show();
                            }
                        });

                        pageNO++;
                        commRecyView.setStatus(SimpleRecyclerView.STATUS_LOAD_MORE_OVER);
                        break;
                }

                if(pullRefresh.isRefreshing()){
                    pullRefresh.finish();
                }

                adapter.notifyDataSetChanged();
            }
        }, 3000);
    }

}
