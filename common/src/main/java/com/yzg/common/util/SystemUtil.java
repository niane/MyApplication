package com.yzg.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by yzg on 2017/6/23.
 */

public class SystemUtil {
    private static Context mContext;

    private SystemUtil(){}

    public static void init(Context context){
        mContext = context;
    }

    /**
     * 返回当前程序版本名versionName
     */
    public static String getVersionName() {
        String versionName = "";
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取当前程序版本号versionCode
     */
    public static int getVersionCode(){
        try {
            PackageInfo pi = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
