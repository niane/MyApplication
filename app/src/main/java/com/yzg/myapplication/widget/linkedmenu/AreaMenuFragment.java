package com.yzg.myapplication.widget.linkedmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yzg.myapplication.R;
import com.yzg.myapplication.app.MyApplication;
import com.yzg.myapplication.model.bean.AreaBean;
import com.yzg.myapplication.model.orm.AreaDao;
import com.yzg.myapplication.model.orm.ormlite.LAreaDao;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yzg on 2017/3/13.
 */

public class AreaMenuFragment extends LinkedMenuFragment<AreaBean, AreaBean> {
    private AreaDao areaDao;
    private List<AreaBean> parentList;
    private List<AreaBean> childList;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        areaDao = MyApplication.getInstance().getAppComponent().getAreaDao();

        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                parentList = areaDao.listByLevel("0");
                childList = areaDao.listByLevel("1");
                subscriber.onNext(null);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                setMenuData(parentList, childList);
            }
        });
    }

    @Override
    protected int getParentItemRes() {
        return R.layout.item_area;
    }

    @Override
    protected int getChildItemRes() {
        return R.layout.item_area_1;
    }

    @Override
    protected void bindParentView(View view, AreaBean data, boolean isSelected) {
        TextView tvArea = (TextView)view.findViewById(R.id.tv_area);
        tvArea.setText(data.getName());
        tvArea.setTextColor(isSelected ? Color.BLUE : Color.BLACK);
    }

    @Override
    protected void bindChildView(View view, AreaBean data) {
        ((TextView)view.findViewById(R.id.tv_area1)).setText(data.getName());
    }

    @Override
    protected boolean isChildOfParent(AreaBean parent, AreaBean child) {
        return child.getParent_id().equals(parent.getId());
    }

    @Override
    protected RecyclerView.LayoutManager getParentLayoutManager() {
        return new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    }

    @Override
    protected RecyclerView.LayoutManager getChildLayoutManager() {
        return new GridLayoutManager(getContext(), 2);
    }
}
