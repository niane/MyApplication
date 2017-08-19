package com.yzg.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yzg.myapplication.R;

/**
 * Created by yzg on 2017/8/19.
 */

public class LazyFragmentTest extends BaseLazyFragment {
    private String tab = "LazyFragmentTest";

    public void setTab(String tab) {
        this.tab = tab;
    }

    @Override
    protected void initData() {
        Log.e(tab, "Loading data.......");
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_lazy_test, null);
        TextView textView = (TextView) view.findViewById(R.id.tv_test);
        textView.setText(tab);
        Log.e(tab, "initView.......");
        return view;
    }
}
