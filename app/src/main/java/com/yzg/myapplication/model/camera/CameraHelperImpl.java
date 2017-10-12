package com.yzg.myapplication.model.camera;

import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by yzg on 2017/9/25.
 */

@SuppressWarnings("deprecation")
public class CameraHelperImpl extends CameraHelper {

    private static final int INVALID_CAMERA_ID = -1;

    private Camera mCamera;

    private final Camera.CameraInfo mCameraInfo = new Camera.CameraInfo();

    private CameraConfigure mCameraConfigure;

    private int mCameraId;

    private int mFacing;

    private boolean mShowingPreview;

    private PreviewCallback mPreviewCallback;

    public CameraHelperImpl(OpenedCallback callback, CameraPreview cameraPreview) {
        super(callback, cameraPreview);
        cameraPreview.setSurfaceChangedCallback(new CameraPreview.SurfaceChangedCallback() {
            @Override
            public void onSurfaceChanged() {
                startPreview();
            }
        });
    }

    private void chooseCamera() {
        for (int i = 0, count = Camera.getNumberOfCameras(); i < count; i++) {
            Camera.getCameraInfo(i, mCameraInfo);
            if (mCameraInfo.facing == mFacing) {
                mCameraId = i;
                return;
            }
        }
        mCameraId = INVALID_CAMERA_ID;
    }

    private void openCamera(){
        if(isOpened()){
            stop();
        }
        mCamera = Camera.open(mCameraId);
        mCameraConfigure = new CameraConfigureImpl(mCamera, mCameraInfo);

        if(mPreviewCallback != null) {
            mCamera.setPreviewCallback(new Camera.PreviewCallback() {
                @Override
                public void onPreviewFrame(byte[] data, Camera camera) {
                    mPreviewCallback.onPreviewFrame(data);
                }
            });
        }

    }

    private void configureCamera(){
        //TODO 设置图片尺寸
        mCameraConfigure.configureDisplayOriention(mCameraPreview.getDisplayOrientation());
        Point size = mCameraConfigure.configurePreviewSize(mCameraPreview.getSurfaceWidth(), mCameraPreview.getSurfaceHeight());
        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    camera.cancelAutoFocus();// 只有加上了这一句，才会自动对焦
                }
            }
        });
//        if(mCameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
//            mCameraPreview.setSurfaceBufferSize(new Point(size.y, size.x));
//        }else {
//            mCameraPreview.setSurfaceBufferSize(size);
//        }
    }

    private void startPreview(){
        try {
            if(mShowingPreview){
                mCamera.stopPreview();
            }

            if(mCameraPreview.isReady()) {
                configureCamera();

                if(mCameraPreview instanceof SurfaceView) {
                    mCamera.setPreviewDisplay(mCameraPreview.getSurfaceHolder());
                }else {
                    mCamera.setPreviewTexture((SurfaceTexture) mCameraPreview.getSurfaceTexture());
                }
                mShowingPreview = true;
                mCamera.startPreview();
            }
        } catch (IOException e) {
            e.printStackTrace();
            stop();
        }
    }

    @Override
    boolean isOpened() {
        return mCamera != null;
    }

    @Override
    public void start() {
        chooseCamera();
        openCamera();
        /* if(mCameraPreview == null){
            mShowingPreview = true;
            mCamera.startPreview();
        }else */
        if(mCameraPreview.isReady()){
            startPreview();
        }
    }

    @Override
    public void stop() {
        if(mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
        mShowingPreview = false;
    }

    @Override
    public void takePicture(TakePictureCallback pictureCallback) {

    }

    @Override
    public void setPreviewCallback(PreviewCallback previewCallback) {
        mPreviewCallback = previewCallback;
    }
}
