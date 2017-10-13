package com.yzg.myapplication.model.camera;

/**
 * Created by yzg on 2017/9/25.
 */
public abstract class CameraHelper {
    protected CameraConfigure mCameraConfigure;

    protected OpenedCallback mOpenedCallback;

    protected CameraPreview mCameraPreview;

    protected float mAspectRatio = 4/3f;

    public CameraHelper(OpenedCallback mOpenedCallback, CameraPreview mCameraPreview) {
        this.mOpenedCallback = mOpenedCallback;
        this.mCameraPreview = mCameraPreview;
    }

    public abstract void setAspectRatio(float ratio);

    public abstract void setDisplayOriention(int oriention);

    abstract boolean isOpened();

    public abstract void start();

    public abstract void stop();

//    abstract void startPreview();
//
//    abstract void stopPreview();

    public abstract void takePicture(TakePictureCallback pictureCallback);

    public abstract void setPreviewCallback(PreviewCallback previewCallback);

    interface PreviewCallback{
        void onPreviewFrame(byte[] data);
    }

    interface TakePictureCallback{
        void onPictureTaken(byte[] data);
    }

    interface OpenedCallback{

        void onOpened(CameraConfigure cameraConfigure);

        void onFail();
    }

    public static CameraHelper create(OpenedCallback openedCallback, CameraPreview cameraPreview){
        return new CameraHelperImpl(openedCallback, cameraPreview);
    }
}
