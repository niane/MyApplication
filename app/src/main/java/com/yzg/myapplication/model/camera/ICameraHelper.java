package com.yzg.myapplication.model.camera;

import android.view.SurfaceHolder;

/**
 * Created by yzg on 2017/9/25.
 */

public interface ICameraHelper {
    void openCamera(int cameraId, SurfaceHolder surfaceHolder);
    void startPreview();
    void closeCamera();
    boolean isOpenning();
}
