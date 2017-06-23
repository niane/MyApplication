package com.yzg.myapplication.ui;

import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yzg.common.base.BaseActivity;
import com.yzg.common.util.TimeUtil;
import com.yzg.myapplication.R;
import com.yzg.simplecalendarview.SimpleCalendarView;

import java.util.Date;

import butterknife.Bind;

/**
 * Created by yzg on 2017/1/5.
 */

public class SimpleCalenderActivity extends BaseActivity {
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.spinner)
    AppCompatSpinner spinner;

    private final String[] models = {"月", "周"};
    @Bind({R.id.month_calendar, R.id.week_calendar})
    SimpleCalendarView[] calendarViews;


    @Override
    protected int getContentLayoutRes() {
        return R.layout.act_simple_calandar;
    }

    @Override
    protected void initViews(View rootView) {
        tvDate.setText(TimeUtil.getYYYYMM(new Date()));

        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, models);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < calendarViews.length; i++) {
                    if (i == position) {
                        calendarViews[i].setVisibility(View.VISIBLE);
                        tvDate.setText(TimeUtil.getYYYYMM(calendarViews[i].getCurrentDate()));
                    } else {
                        calendarViews[i].setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        calendarViews[0].initDate(new Date());
        calendarViews[0].setOnMonthChangeListener(new SimpleCalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(Date date, int PageNum) {
                tvDate.setText(TimeUtil.getYYYYMM(date));
            }
        });

        calendarViews[1].initDate(new Date(), SimpleCalendarView.Mode.week);
        calendarViews[1].setOnMonthChangeListener(new SimpleCalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(Date date, int PageNum) {
                tvDate.setText(TimeUtil.getYYYYMM(date));
            }
        });
    }

    @Override
    protected void initInject() {

    }
}
