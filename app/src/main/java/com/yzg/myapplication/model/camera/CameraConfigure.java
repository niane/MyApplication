package com.yzg.myapplication.model.camera;

import android.graphics.Point;

import java.util.List;

/**
 * Created by yzg on 2017/9/29.
 */

public interface CameraConfigure {

    List<Point> getSupportedPreviewSizes();

    List<Point> getSupportedPictureSizes();
}
