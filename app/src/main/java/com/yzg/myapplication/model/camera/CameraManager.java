package com.yzg.myapplication.model.camera;

import android.content.Context;
import android.view.SurfaceHolder;

/**
 * Created by yzg on 2017/9/25.
 */

public class CameraManager {
    private ICameraHelper cameraHelper;
    private Context context;

    public CameraManager(Context context) {
        this.context = context;
        cameraHelper = new CameraHelper();
    }

    public void openCamera(SurfaceHolder surfaceHolder){
        if(!cameraHelper.isOpenning()){
            cameraHelper.openCamera(0, surfaceHolder);
            cameraHelper.startPreview();
        }
    }

    public void closeCamera(){
        if(cameraHelper.isOpenning()){
            cameraHelper.closeCamera();
        }
    }

}
