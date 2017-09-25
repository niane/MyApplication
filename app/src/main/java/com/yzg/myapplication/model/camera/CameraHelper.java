package com.yzg.myapplication.model.camera;

import android.hardware.Camera;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * Created by yzg on 2017/9/25.
 */

public class CameraHelper implements ICameraHelper {
    private Camera mCamera;

    @Override
    public void openCamera(int cameraId, SurfaceHolder surfaceHolder) {
        mCamera = Camera.open();
        try {
            mCamera.setPreviewDisplay(surfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startPreview() {
        if(mCamera != null){
            mCamera.startPreview();
        }
    }

    @Override
    public void closeCamera() {
        if(mCamera != null){
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    public boolean isOpenning() {
        return mCamera != null;
    }
}
