package com.yzg.myapplication.model.camera;

import android.view.SurfaceHolder;

/**
 * Created by yzg on 2017/10/11.
 */

public interface CameraPreview {

    boolean isReady();

    SurfaceHolder getSurfaceHolder();

    Object getSurfaceTexture();

    void setSurfaceChangedCallback(SurfaceChangedCallback changedCallback);

    interface SurfaceChangedCallback{
        void onSurfaceChanged();
    }
}
