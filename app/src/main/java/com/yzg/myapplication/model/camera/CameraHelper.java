package com.yzg.myapplication.model.camera;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * Created by yzg on 2017/9/25.
 */

public class CameraHelper implements ICameraHelper {
    private Context mContext;
    private Camera mCamera;
    private PreviewCallback mPreviewCallback;

    private int state = STATE_RELEASED;

    public CameraHelper(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void openCamera(int cameraId, SurfaceHolder surfaceHolder) {
        if (state == STATE_RELEASED) {
            try {
                state = STATE_OPENNING;
                mCamera = Camera.open();
                if (mCamera != null) {
                    mCamera.setPreviewDisplay(surfaceHolder);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
                releaseCamera();
            }
        }
    }

    @Override
    public void releaseCamera() {
        state = STATE_RELEASED;
        if(mCamera != null) {
            mCamera.release();
            mCamera = null;
        }

    }

    @Override
    public boolean isOpenning() {
        return state != STATE_RELEASED;
    }

    @Override
    public boolean isPreviewing() {
        return state == STATE_PREVIEWING;
    }

    @Override
    public void startPreview(PreviewCallback previewCallback) {
        mPreviewCallback = previewCallback;
        if (state == STATE_OPENNING) {
            state = STATE_PREVIEWING;

            if (mPreviewCallback != null) {
                mCamera.setPreviewCallback(new Camera.PreviewCallback() {
                    @Override
                    public void onPreviewFrame(byte[] data, Camera camera) {
                        mPreviewCallback.onPreviewFrame(data);
                    }
                });
            }
            mCamera.startPreview();
        }
    }

    @Override
    public void stopPreview() {
        if (state == STATE_PREVIEWING) {
            state = STATE_OPENNING;
            mCamera.stopPreview();
        }
    }

    @Override
    public void takePicture(TakePictureCallback pictureCallback) {

    }
}
