package com.yzg.myapplication.model.camera;


import android.view.SurfaceHolder;

/**
 * Created by yzg on 2017/9/25.
 */

public interface ICameraHelper {
    int STATE_RELEASED = 0;
    int STATE_OPENNING = 1;
    int STATE_PREVIEWING = 2;

    void openCamera(int cameraId, SurfaceHolder surfaceHolder);

    void releaseCamera();

    boolean isOpenning();

    boolean isPreviewing();

    void startPreview(PreviewCallback previewCallback);

    void stopPreview();

    void takePicture(TakePictureCallback pictureCallback);

    interface PreviewCallback{
        void onPreviewFrame(byte[] data);
    }

    interface TakePictureCallback{
        void onPictureTaken(byte[] data);
    }

    interface OpenCallback{
        void onOpened(ICameraConfigure cameraConfigure);

        void onFail();
    }
}
