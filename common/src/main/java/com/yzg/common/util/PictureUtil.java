package com.yzg.common.util;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by yzg on 2017/2/28. <br/>
 *
 * 图片工具类 <br/>
 * 使用前须初始化
 */

public class PictureUtil {
    private static Context mContext;

    public static void init(Context context){
        mContext = context;
    }

    /**
     * 获取图片宽高
     * @param imgRes
     * @return width:[0]    height:[1]
     */
    public static int[] getPicSize(@DrawableRes int imgRes){
        int[] size = new int[2];

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        opts.inSampleSize = 1;
        BitmapFactory.decodeResource(mContext.getResources(), imgRes, opts);
        size[0] = opts.outWidth;
        size[1] = opts.outHeight;

        return size;
    }

    /**
     * 获取图片宽高
     * @param filePath
     * @return filePath为null或图片文件不存在返回null width:[0]    height:[1]
     */
    public static int[] getPicSize(String filePath){
        if(TextUtils.isEmpty(filePath)){
            return null;
        }

        File file = new File(filePath);
        if(!file.exists() || !file.isFile()){
            return null;
        }

        int[] size = new int[2];

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        opts.inSampleSize = 1;
        BitmapFactory.decodeFile(filePath, opts);
        size[0] = opts.outWidth;
        size[1] = opts.outHeight;

        return size;
    }
}
