package com.yzg.myapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.yzg.myapplication.model.camera.CameraPreview;

/**
 * Created by yzg on 2017/10/11.
 */

public class SurfacePreview extends SurfaceView implements SurfaceHolder.Callback, CameraPreview{
    private static final String Tag = SurfacePreview.class.getSimpleName();
    private int mWidth, mHeight;
    private SurfaceChangedCallback changedCallback;

    public SurfacePreview(Context context) {
        super(context);
        init();
    }

    public SurfacePreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SurfacePreview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(21)
    public SurfacePreview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e(Tag, "surfaceCreated");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e(Tag, "surfaceChanged");
        mWidth = width;
        mHeight = height;
        changedCallback.onSurfaceChanged();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e(Tag, "surfaceDestroyed");
        mHeight = 0;
        mWidth = 0;
    }

    @Override
    public boolean isReady() {
        return mWidth != 0 && mHeight != 0;
    }

    @Override
    public SurfaceHolder getSurfaceHolder() {
        return super.getHolder();
    }

    @Override
    public Object getSurfaceTexture() {
        return null;
    }

    @Override
    public void setSurfaceChangedCallback(SurfaceChangedCallback changedCallback) {
        this.changedCallback = changedCallback;
    }

    @Override
    public int getDisplayOrientation() {
        return ViewCompat.getDisplay(this).getRotation();
    }

    @Override
    public int getSurfaceWidth() {
        return mWidth;
    }

    @Override
    public int getSurfaceHeight() {
        return mHeight;
    }

    @Override
    public void setSurfaceBufferSize(Point size) {
        if(size.x == mWidth && size.y == mHeight) return;

        mWidth = size.x;
        mHeight = size.y;
        getHolder().setFixedSize(size.x, size.y);
    }
}
