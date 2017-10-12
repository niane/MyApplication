package com.yzg.myapplication.model.camera;

import android.graphics.Point;

import java.util.List;

/**
 * Created by yzg on 2017/9/29.
 */

public interface CameraConfigure {
    int[] DISPLAY_ORIENTATIONS = new int[]{0, 90, 180, 270};

    List<Point> getSupportedPreviewSizes();

    List<Point> getSupportedPictureSizes();

    Point getOptimalPreviewSize(int width, int height);

    Point configurePreviewSize(int width, int height);

    void configureDisplayOriention(int displayOriention);
}
