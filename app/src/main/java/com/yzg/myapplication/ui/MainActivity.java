package com.yzg.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.yzg.common.base.BaseActivity;
import com.yzg.myapplication.R;
import com.yzg.myapplication.rx.RxBus;
import com.yzg.myapplication.util.PermissionUtils;
import com.yzg.pulltorefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Manifest;

import butterknife.Bind;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

public class MainActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private final String TAG = this.getClass().getSimpleName();
    @Bind(R.id.index_list)
    ListView indexList;
    @Bind(R.id.pull_refresh)
    PullToRefreshLayout pullRefresh;
    @Bind(R.id.tool_bar)
    Toolbar toolBar;
    @Bind(R.id.tool_bar_title)
    TextView toolBarTitle;

    private SimpleAdapter mAdapter;
    private ArrayList<Map<String, Object>> mList = new ArrayList<>();

    @Override
    protected int getContentLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(View rootView) {
//      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//      getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolBar.setTitle("");
        toolBarTitle.setText(getResources().getText(R.string.app_name));
        setSupportActionBar(toolBar);

        initActivityies();
        mAdapter = new SimpleAdapter(this, mList, android.R.layout.simple_list_item_1, new String[]{"name"}, new int[]{android.R.id.text1});
        indexList.setAdapter(mAdapter);
        indexList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((Activities) mList.get(position).get("activity")).launch(MainActivity.this);
            }
        });
        pullRefresh.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefresh.finish();
                    }
                }, 3000);
            }
        });

        Log.e(TAG, "Main Thread id " + Thread.currentThread().getId());

        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                Log.e(TAG, "Observable Thread id " + Thread.currentThread().getId());
                subscriber.onNext("This is a emited message");
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        Log.e(TAG, "AndroidSchedulers Thread id " + Thread.currentThread().getId());
                    }
                });

        RxBus.getInstance().onEvent(String.class, this, new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "Event listener3:" + s + ";-------- thread id: " + Thread.currentThread().getId());
            }
        });
        RxBus.getInstance().post("This is a RxBus event test");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unsubscribe(this);
    }

    @Override
    protected void initInject() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting:
                PermissionUtils.requestPermission(MainActivity.this, "是否允许SD卡读写权限?", permissionCallback, 2, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA);
//                Toast.makeText(this, "Clicked setting", Toast.LENGTH_SHORT).show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initActivityies() {
        Activities[] activities = Activities.values();
        for (int i = 0; i < activities.length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name", activities[i].name);
            map.put("activity", activities[i]);
            mList.add(map);
        }
    }

    enum Activities {
        COORDINATOR_RECYCLE_VIEW(CoordinatorActivity.class, "Coordinator_recycle"),
        SIMPLE_CALENDAR(SimpleCalenderActivity.class, "SimpleCalender"),
        PULL_REFRESH(PullRefreshTest.class, "PullRefreshTest"),
        SIMPLE_RECYCLE_VIEW(SimpleRecycleViewTest.class, "SimpleRecyViewTest"),
        LINKED_MENU(LinkedMenuTest.class, "LinkedMenu"),
        TEST_DAGGER(ExampleMvp.class, "TestDagger"),
        IMAGE_WATER(ImageWaterActivity.class, "ImageWater");

        private Class cls;
        private String name;

        Activities(Class<?> cls, String name) {
            this.cls = cls;
            this.name = name;
        }

        public void launch(Context context) {
            Intent intent = new Intent(context, cls);
            context.startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private PermissionUtils.PermissionCallback permissionCallback = new PermissionUtils.PermissionCallback() {
        @Override
        public void onPermissionGranted(int requestCode) {
            Toast.makeText(MainActivity.this, "成功取得权限", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(int requestCode) {
            Toast.makeText(MainActivity.this, "权限获取失败", Toast.LENGTH_SHORT).show();
        }
    };
}
