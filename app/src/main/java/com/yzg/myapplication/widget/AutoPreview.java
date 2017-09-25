package com.yzg.myapplication.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

import com.yzg.myapplication.R;
import com.yzg.myapplication.model.camera.CameraManager;

/**
 * Created by yzg on 2017/9/25.
 */

public class AutoPreview extends FrameLayout implements SurfaceHolder.Callback {
    private View rootView;

    private Context mContext;
    private CameraManager cameraManager;
    private boolean hasSurface;

    public AutoPreview(@NonNull Context context) {
        super(context, null);
    }

    public AutoPreview(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context){
        this.mContext = context;
        rootView = LayoutInflater.from(context).inflate(R.layout.preview_layout, this, true);
    }

    public void setCameraManager(CameraManager cameraManager){
        this.cameraManager = cameraManager;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(!hasSurface){
            hasSurface = true;
            cameraManager.openCamera(holder);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //退出该页面或退出应用时会调用，跳转到其他页面并不会调用
        hasSurface = false;
        cameraManager.closeCamera();
    }

    /**在activity onResume时调用*/
    public void onResume(){
        SurfaceView surfaceView = (SurfaceView) rootView.findViewById(R.id.preview_surface);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if(hasSurface){ //由app其他页面返回
            cameraManager.openCamera(surfaceHolder);
        }else {
            //跳转到该页面或重新启动app
            surfaceHolder.addCallback(this);
        }
    }

    /**在activity onStop时调用*/
    public void onStop(){
        cameraManager.closeCamera();
    }
}
