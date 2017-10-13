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

@SuppressWarnings("deprecation")
public class CameraConfigureImpl implements CameraConfigure {
    private static final String Tag = CameraConfigureImpl.class.getSimpleName();
    private Camera mCamera;
    private Camera.CameraInfo mCameraInfo;
    private int mDisplayOriention;
    private Camera.Parameters mParameters;

    public CameraConfigureImpl(Camera mCamera, Camera.CameraInfo cameraInfo) {
        this.mCamera = mCamera;
        this.mCameraInfo = cameraInfo;
        mParameters = mCamera.getParameters();
    }

    @Override
    public List<Point> getSupportedPreviewSizes() {
        List<Camera.Size> sizes = mParameters.getSupportedPreviewSizes();
        List<Point> result = new ArrayList<>();
        for (Camera.Size size : sizes) {
            result.add(new Point(size.width, size.height));
        }

        return result;
    }

    @Override
    public List<Point> getSupportedPictureSizes() {
        List<Camera.Size> sizes = mParameters.getSupportedPictureSizes();
        List<Point> result = new ArrayList<>();
        for (Camera.Size size : sizes) {
            result.add(new Point(size.width, size.height));
        }
        Log.e(Tag, "Supported picture sizes : " + result.toString());
        return result;
    }

    private boolean isLandscape() {
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
        if (isLandscape()) {
            desiredHeight = width;
            desiredWidth = height;
        } else {
            desiredWidth = width;
            desiredHeight = height;
        }

        Point selectedSize = sizes.get(sizes.size() - 1);
        for (Point size : sizes) {
            if (size.x / (float)size.y == desiredHeight / (float)desiredWidth) {
                selectedSize = size;
            }
        }
        return selectedSize;
    }

    @Override
    public Point configurePreviewSize(int width, int height) {
        Log.e(Tag, "Surface size : " + width + "*" + height);
        Point size = getOptimalPreviewSize(width, height);
        mParameters.setPreviewSize(size.x, size.y);
        mCamera.setParameters(mParameters);
        Log.e(Tag, "Configured preview size : " + size.x + "*" + size.y);
        return size;
    }

    @Override
    public void configureDisplayOriention(int displayOriention) {
        mDisplayOriention = displayOriention;
        int degrees = 0;
        if (mCameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            degrees = (360 - (mCameraInfo.orientation + DISPLAY_ORIENTATIONS[displayOriention]) % 360) % 360;
        } else {  // back-facing
            degrees = (mCameraInfo.orientation - DISPLAY_ORIENTATIONS[displayOriention] + 360) % 360;
        }
        mCamera.setDisplayOrientation(degrees);
    }

    @Override
    public void setAutoFocus(boolean autoFocus) {
        final List<String> modes = mParameters.getSupportedFocusModes();
        if (autoFocus && modes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            mParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        } else if (modes.contains(Camera.Parameters.FOCUS_MODE_FIXED)) {
            mParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_FIXED);
        } else if (modes.contains(Camera.Parameters.FOCUS_MODE_INFINITY)) {
            mParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_INFINITY);
        } else {
            mParameters.setFocusMode(modes.get(0));
        }
        mCamera.setParameters(mParameters);
    }

    @Override
    public float getOptimalAspectRatio(float ratio) {
        float minDiff = 100f;
        float desiredRatio;
        Point selectedSize = new Point(3, 4);

        if(isLandscape()){
            desiredRatio = 1 / ratio;
        }else {
            desiredRatio = ratio;
        }

        List<Point> sizes = getSupportedPreviewSizes();
        for(Point size : sizes){
            if(Math.abs(size.x/(float)size.y - desiredRatio) < minDiff){
                minDiff = Math.abs(size.x/(float)size.y - desiredRatio);
                selectedSize = size;
            }
        }

        if(isLandscape()){
            return selectedSize.y/(float)selectedSize.x;
        }else {
            return selectedSize.x/(float)selectedSize.y;
        }
    }
}
