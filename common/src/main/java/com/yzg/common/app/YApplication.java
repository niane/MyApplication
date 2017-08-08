package com.yzg.common.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.yzg.common.util.DisplayUtil;
import com.yzg.common.util.ConnectionUtil;
import com.yzg.common.util.PictureUtil;
import com.yzg.common.util.SystemUtil;

/**
 * Created by yzg on 2017/5/26.
 */

public class YApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initTools();
    }

    protected void initTools(){
        DisplayUtil.init(this);
        PictureUtil.init(this);
        ConnectionUtil.init(this);
        SystemUtil.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
