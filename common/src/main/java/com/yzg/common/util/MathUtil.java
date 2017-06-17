package com.yzg.common.util;

/**
 * Created by yzg on 2017/1/17.
 *
 * 数学工具类
 */

public class MathUtil {
    public static int constrain(int amount, int low, int high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

    public static float constrain(float amount, float low, float high) {
        return amount < low ? low : (amount > high ? high : amount);
    }
}
