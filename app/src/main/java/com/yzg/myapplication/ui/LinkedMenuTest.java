package com.yzg.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.yzg.myapplication.R;
import com.yzg.myapplication.widget.linkedmenu.AreaMenuFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yzg on 2017/3/13.
 */

public class LinkedMenuTest extends AppCompatActivity {
    @Bind(R.id.frame_layout)
    FrameLayout frameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_linked_menu);
        ButterKnife.bind(this);

        AreaMenuFragment fragment = new AreaMenuFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, fragment).commit();

    }
}
