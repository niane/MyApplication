package com.yzg.myapplication.model.camera;

import android.graphics.Point;
import android.graphics.PointF;
import android.hardware.Camera;
import android.util.Log;
import android.util.Size;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yzg on 2017/9/29.
 */

public class CameraConfigureImpl implements CameraConfigure {
    private static final String Tag = CameraConfigureImpl.class.getSimpleName();
    private Camera mCamera;
    private Camera.CameraInfo mCameraInfo;
    private int mDisplayOriention;

    public CameraConfigureImpl(Camera mCamera, Camera.CameraInfo cameraInfo) {
        this.mCamera = mCamera;
        this.mCameraInfo = cameraInfo;
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

    private boolean isLandscape(){
        return DISPLAY_ORIENTATIONS[mDisplayOriention] == 90 ||
                DISPLAY_ORIENTATIONS[mDisplayOriention] == 270;
    }

    @Override
    public Point getOptimalPreviewSize(int width, int height) {
        List<Point> sizes = getSupportedPreviewSizes();
        Collections.sort(sizes, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.x * o1.y - o2.x * o2.y;
            }
        });
        Log.e(Tag, "Supported preview sizes : " + sizes.toString());
        int desiredWidth;
        int desiredHeight;
        if(isLandscape()){
            desiredHeight = width;
            desiredWidth = height;
        }else {
            desiredWidth = width;
            desiredHeight = height;
        }

        for(Point size : sizes){
            if(size.x >= desiredWidth && size.y >= desiredHeight){
                return size;
            }
        }
        return sizes.get(sizes.size() - 1);
    }

    @Override
    public Point configurePreviewSize(int width, int height) {
        Point size = getOptimalPreviewSize(width, height);
        mCamera.getParameters().setPreviewSize(size.x, size.y);
        Log.e(Tag, "Configured preview size : " + size.x + "*" + size.y);
        return size;
    }

    @Override
    public void configureDisplayOriention(int displayOriention) {
        mDisplayOriention = displayOriention;
        int degrees = 0;
        if (mCameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            degrees =  (360 - (mCameraInfo.orientation + DISPLAY_ORIENTATIONS[displayOriention]) % 360) % 360;
        } else {  // back-facing
            degrees =  (mCameraInfo.orientation - DISPLAY_ORIENTATIONS[displayOriention] + 360) % 360;
        }
        mCamera.setDisplayOrientation(degrees);
    }


}
