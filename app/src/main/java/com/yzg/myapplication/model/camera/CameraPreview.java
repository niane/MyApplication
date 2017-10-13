package com.yzg.myapplication.model.camera;

import android.graphics.Point;
import android.view.SurfaceHolder;

/**
 * Created by yzg on 2017/10/11.
 */

public interface CameraPreview {

    boolean isReady();

    SurfaceHolder getSurfaceHolder();

    Object getSurfaceTexture();

    void setSurfaceChangedCallback(SurfaceChangedCallback changedCallback);

    int getDisplayOrientation();

    int getSurfaceWidth();

    int getSurfaceHeight();

    void setAspectRatio(float ratio);

//    void setSurfaceBufferSize(Point size);

    interface SurfaceChangedCallback{
        void onSurfaceChanged();

        void onAttachedWindow();
    }
}
