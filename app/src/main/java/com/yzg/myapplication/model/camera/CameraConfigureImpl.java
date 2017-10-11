package com.yzg.myapplication.model.camera;

import android.graphics.Point;
import android.graphics.PointF;
import android.hardware.Camera;
import android.util.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzg on 2017/9/29.
 */

public class CameraConfigureImpl implements CameraConfigure {

    private Camera mCamera;

    public CameraConfigureImpl(Camera mCamera) {
        this.mCamera = mCamera;
    }

    @Override
    public List<Point> getSupportedPreviewSizes() {
        List<Camera.Size> sizes = mCamera.getParameters().getSupportedPreviewSizes();
        List<Point> result = new ArrayList<>();
        for(Camera.Size size : sizes){
            result.add(new Point(size.width, size.height));
        }

        return result;
    }

    @Override
    public List<Point> getSupportedPictureSizes() {
        List<Camera.Size> sizes = mCamera.getParameters().getSupportedPictureSizes();
        List<Point> result = new ArrayList<>();
        for(Camera.Size size : sizes){
            result.add(new Point(size.width, size.height));
        }

        return result;
    }
}
