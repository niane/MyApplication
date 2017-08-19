package com.yzg.myapplication.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yzg.common.base.BaseFragment;
import com.yzg.myapplication.R;
import com.yzg.myapplication.adapter.FragmentAdapter;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by yzg on 2017/8/19.
 */

public class FragmentViewPager extends BaseFragment {
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getContentLayoutRes() {
        return R.layout.frag_viewpager;
    }

    @Override
    protected void initViews(View rootView) {
        for(int i = 0; i < 8; i++){
            LazyFragmentTest fragment = new LazyFragmentTest();
            fragment.setTab("Tab"+i);
            fragments.add(fragment);
        }

        final FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragments);

        viewpager.setAdapter(adapter);
    }
}
