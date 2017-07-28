package com.yzg.myapplication.ui;

import android.view.View;
import android.widget.FrameLayout;

import com.yzg.common.base.BaseActivity;
import com.yzg.myapplication.R;
import com.yzg.myapplication.widget.linkedmenu.AreaMenuFragment;

import butterknife.Bind;

/**
 * Created by yzg on 2017/3/13.
 */

public class LinkedMenuTest extends BaseActivity {
    @Bind(R.id.frame_layout)
    FrameLayout frameLayout;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.act_linked_menu;
    }

    @Override
    protected void initViews(View rootView) {
        AreaMenuFragment fragment = new AreaMenuFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, fragment).commit();
    }
}
