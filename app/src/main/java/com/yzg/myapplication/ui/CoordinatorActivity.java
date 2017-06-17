package com.yzg.myapplication.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yzg.myapplication.R;
import com.yzg.myapplication.adapter.FragmentAdapter;
import com.yzg.myapplication.fragment.RecyclerFragmentTest;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CoordinatorActivity extends AppCompatActivity {
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.app_bar)
    AppBarLayout appBarLayout;
    @Bind(R.id.tv_search)
    TextView tvSearch;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] tabs = new String[]{"Tab1", "Tab2", "Tab3", "Tab4", "Tab5", "Tab6", "Tab7", "Tab8"
                                            ,"Tab1", "Tab2", "Tab3", "Tab4", "Tab5", "Tab6", "Tab7", "Tab8"
                                            ,"Tab1", "Tab2", "Tab3", "Tab4", "Tab5", "Tab6", "Tab7", "Tab8"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        ButterKnife.bind(this);
        init();
    }

    private void init(){

        for(int i = 0; i < tabs.length; i++){
            RecyclerFragmentTest fragment = new RecyclerFragmentTest();
            fragments.add(fragment);
        }

        final FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, tabs);

        tabLayout.setSmoothScrollingEnabled(true);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);


        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CoordinatorActivity.this, "Click on Search....", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
