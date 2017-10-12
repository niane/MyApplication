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

    @Override
    public Point getOptimalPreviewSize(int width, int height) {
        return null;
    }

    @Override
    public Point configurePreviewSize(int width, int height) {
        return null;
    }

    @Override
    public void configureDisplayOriention(int displayOriention) {

    }
}
