package com.yzg.myapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.TextureView;

import com.yzg.myapplication.model.camera.CameraPreview;

/**
 * Created by yzg on 2017/10/11.
 */

@TargetApi(14)
public class TexturePreview extends TextureView implements TextureView.SurfaceTextureListener, CameraPreview {
    private static final String Tag = TexturePreview.class.getSimpleName();
    private int mWidth, mHeight;
    private SurfaceChangedCallback changedCallback;

    public TexturePreview(Context context) {
        super(context);
        init();
    }

    public TexturePreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TexturePreview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(21)
    public TexturePreview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        setSurfaceTextureListener(this);
    }

    @Override
    public boolean isReady() {
        return mWidth != 0 && mHeight != 0;
    }

    @Override
    public SurfaceHolder getSurfaceHolder() {
        return null;
    }

    @Override
    public void setSurfaceChangedCallback(SurfaceChangedCallback changedCallback) {
        this.changedCallback = changedCallback;
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Log.e(Tag, "onSurfaceTextureAvailable");
        mWidth = width;
        mHeight = height;
        changedCallback.onSurfaceChanged();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        Log.e(Tag, "onSurfaceTextureSizeChanged");
        mWidth = width;
        mHeight = height;
        changedCallback.onSurfaceChanged();
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        Log.e(Tag, "onSurfaceTextureDestroyed");
        mWidth = 0;
        mHeight = 0;
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
}
