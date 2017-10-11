package com.yzg.myapplication.model.camera;

import android.graphics.Point;

import java.util.List;

/**
 * Created by yzg on 2017/9/29.
 */

public class Camera2Configure implements CameraConfigure {
//    @Override
//    public void setPreviewCallback(CameraHelper.PreviewCallback previewCallback) {
//
//    }
//
//    @Override
//    public CameraHelper.PreviewCallback getPreviewCallback() {
//        return null;
//    }

    @Override
    public List<Point> getSupportedPreviewSizes() {
        return null;
    }

    @Override
    public List<Point> getSupportedPictureSizes() {
        return null;
    }
}
