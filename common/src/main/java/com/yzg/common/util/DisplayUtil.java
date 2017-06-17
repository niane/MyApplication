package com.yzg.common.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by yzg on 2016/10/17. <br/>
 * 显示屏工具类 <br/>
 * 使用前须初始化
 */

public class DisplayUtil {

    private static Context mContext;

    private static DisplayMetrics displayMetrics;

    public static void init(Context context){
        mContext = context;
        displayMetrics = context.getResources().getDisplayMetrics();
    }

    /**获取手机屏幕宽度(Pixel)*/
    public static int getScreenWidth(){
        return displayMetrics.widthPixels;
    }

    /**获取手机屏幕高度(Pixel)*/
    public static int getScreenHeight(){
        return displayMetrics.heightPixels;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = displayMetrics.density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = displayMetrics.density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float getDensity(){
        return displayMetrics.density;
    }
}
