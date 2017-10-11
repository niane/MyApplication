package com.yzg.myapplication.model.camera;

import android.content.Context;
import android.view.SurfaceHolder;

/**
 * Created by yzg on 2017/9/25.
 */

public class CameraManager {
    private Context context;
    private CameraHelper cameraHelper;
    private CameraHelper.PreviewCallback previewCallback;

    public CameraManager(Context context) {
        this.context = context;
//        cameraHelper = new CameraHelperImpl(context);
    }

    public synchronized void openCamera(SurfaceHolder surfaceHolder){
//        if(!cameraHelper.isOpenning()){
//            cameraHelper.openCamera(0, surfaceHolder);
//        }
    }

    public synchronized void startPreview(){
//        if(!cameraHelper.isPreviewing()) {
//            cameraHelper.startPreview(previewCallback);
//        }
    }

    public synchronized void stopPreview(){
//        if(cameraHelper.isPreviewing()){
//            cameraHelper.stopPreview();
//        }
    }

    public synchronized void releaseCamera(){
//        if(cameraHelper.isOpenning()){
//            cameraHelper.releaseCamera();
//        }
    }

    public void setPreviewCallback(CameraHelper.PreviewCallback previewCallback) {
        this.previewCallback = previewCallback;
    }
}
