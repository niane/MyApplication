package com.yzg.myapplication.model.camera;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.*;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.Size;
import android.view.SurfaceHolder;

import java.util.Arrays;

/**
 * Created by yzg on 2017/9/26.
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class Camera2HelperImpl extends CameraHelper {
    private String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private android.hardware.camera2.CameraManager mCameraManager;
    private CameraDevice mCameraDevice;
    private CameraCaptureSession mCaptureSession;
    private CaptureRequest.Builder mPreviewRequestBuilder;
    private PreviewCallback mPreviewback;

//    private int state = STATE_RELEASED;

    private SurfaceHolder mSurfaceHolder;
    private Size previewSize;

    public Camera2HelperImpl(OpenedCallback mOpenedCallback, CameraPreview mCameraPreview) {
        super(mOpenedCallback, mCameraPreview);
    }

//    private CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {
//        @Override
//        public void onOpened(@NonNull CameraDevice camera) {
//            Log.e(TAG, "Opened camera success");
//            if(camera == null) return;
//
//            state = STATE_OPENNING;
//            mCameraDevice = camera;
//            createPreviewSession();
//        }
//
//        @Override
//        public void onDisconnected(@NonNull CameraDevice camera) {
//            Log.e(TAG, "Open camera onDisconnected");
//            releaseCamera();
//        }
//
//        @Override
//        public void onError(@NonNull CameraDevice camera, int error) {
//            Log.e(TAG, "Open camera onError");
//            releaseCamera();
//        }
//    };

//    public Camera2HelperImpl(Context mContext) {
//        this.mContext = mContext;
//    }
//
//    @Override
//    public void openCamera(int cameraId, SurfaceHolder surfaceHolder) {
//        Log.d(TAG, "Openning camera");
//        if (state > STATE_RELEASED) return;
//
//        mSurfaceHolder = surfaceHolder;
//        mSurfaceHolder.setFixedSize(1280, 720);
//        if (mCameraManager == null) {
//            mCameraManager = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
//        }
//
//        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        try {
//            mCameraManager.openCamera(cameraId + "", mStateCallback, null);
//        } catch (CameraAccessException e) {
//            e.printStackTrace();
//            releaseCamera();
//        }
//    }
//
//    @Override
//    public void startPreview(PreviewCallback previewCallback) {
//        this.mPreviewback = previewCallback;
//        if (state == STATE_OPENNING) {
//            if(mCaptureSession == null){
//                createPreviewSession();
//            }else {
//                try {
//                    state = STATE_PREVIEWING;
//                    mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE,
//                            CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
//                    mCaptureSession.setRepeatingRequest(mPreviewRequestBuilder.build(), null, null);
//                } catch (CameraAccessException e) {
//                    e.printStackTrace();
//                    state = STATE_OPENNING;
//                }
//            }
//        }
//    }
//
//    @Override
//    public void stopPreview() {
//        if (state == STATE_PREVIEWING) {
//            try {
//                state = STATE_OPENNING;
//                if (mCaptureSession != null)
//                    mCaptureSession.stopRepeating();
//            } catch (CameraAccessException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    boolean isOpened() {
        return false;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void takePicture(TakePictureCallback pictureCallback) {

    }

    @Override
    public void setPreviewCallback(PreviewCallback previewCallback) {

    }

//    @Override
//    public void releaseCamera() {
//        state = STATE_RELEASED;
//        if(mCaptureSession != null){
//            mCaptureSession.close();
//            mCaptureSession = null;
//        }
//        if (mCameraDevice != null) {
//            mCameraDevice.close();
//            mCameraDevice = null;
//        }
//    }
//
//    @Override
//    public boolean isOpenning() {
//        return state != STATE_RELEASED;
//    }
//
//    @Override
//    public boolean isPreviewing() {
//        return state == STATE_PREVIEWING;
//    }

//    private void createPreviewSession() {
//        try {
//            mPreviewRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
//            mPreviewRequestBuilder.addTarget(mSurfaceHolder.getSurface());
//
//            mCameraDevice.createCaptureSession(Arrays.asList(mSurfaceHolder.getSurface()), new CameraCaptureSession.StateCallback() {
//                @Override
//                public void onConfigured(@NonNull CameraCaptureSession session) {
//                    Log.e(TAG, "CameraCaptureSession Configured success");
//                    mCaptureSession = session;
//                    startPreview(mPreviewback);
//                }
//
//                @Override
//                public void onConfigureFailed(@NonNull CameraCaptureSession session) {
//                    releaseCamera();
//                    Log.e(TAG, "CameraCaptureSession Configure Failed");
//                }
//            }, null);
//        } catch (CameraAccessException e) {
//            e.printStackTrace();
//            Log.e(TAG, "CameraCaptureSession create failed");
//            releaseCamera();
//        }
//    }
}
