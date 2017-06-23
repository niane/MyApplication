package com.yzg.common.util;

import android.util.Log;

/**
 * Created by yzg on 2017/4/28.
 */

public class YLog {
    public static boolean isDebug() {
        return DEBUG;
    }

    public static void setDEBUG(boolean DEBUG) {
        YLog.DEBUG = DEBUG;
    }

    private static boolean DEBUG = true;

    private YLog(){}

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, Throwable throwable) {
        if (DEBUG) {
            Log.e(tag, throwable.getMessage());
        }
    }
}
