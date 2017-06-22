package com.yzg.common.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzg on 2017/4/28.
 *
 * 下游Application 须继承{@link com.yzg.common.app.YApplication}
 */

public class ActivityCollector {
    private static final String TAG = ActivityCollector.class.getSimpleName();

    private ActivityCollector(){}

    private static List<Activity> activities = new ArrayList<>();

    private static Activity currentActivity;

    public static void addActivity(Activity activity) {
        YLog.d(TAG, "addActivity(" + activity.getClass().getSimpleName() + ")");
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        YLog.d(TAG, "removeActivity(" + activity.getClass().getSimpleName() + ")");
        activities.remove(activity);
    }

    public static void startActivity(Activity activity){
        YLog.d(TAG, "startActivity(" + activity.getClass().getSimpleName() + ")");
        currentActivity = activity;
    }

    public Activity getCurrentActivity(){
        return currentActivity;
    }
}
