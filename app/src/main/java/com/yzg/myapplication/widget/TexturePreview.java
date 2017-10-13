package com.yzg.myapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.TextureView;

import com.yzg.myapplication.model.camera.CameraPreview;

/**
 * Created by yzg on 2017/10/11.
 */

@TargetApi(15)
public class TexturePreview extends TextureView implements TextureView.SurfaceTextureListener, CameraPreview {
    private static final String Tag = TexturePreview.class.getSimpleName();
    private int mWidth, mHeight;
    private SurfaceChangedCallback changedCallback;
    private float mAspectRatio;

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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(Tag, "onMeasure");
        if(mAspectRatio != 0) {
            final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            final int heightMode = MeasureSpec.getMode(heightMeasureSpec);

            if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
                int height = (int) (MeasureSpec.getSize(widthMeasureSpec) * mAspectRatio);
                if (heightMode == MeasureSpec.AT_MOST) {
                    height = Math.min(height, MeasureSpec.getSize(heightMeasureSpec));
                }
                super.onMeasure(widthMeasureSpec,
                        MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
            } else if (widthMode != MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
                int width = (int) (MeasureSpec.getSize(heightMeasureSpec) / mAspectRatio);
                if (widthMode == MeasureSpec.AT_MOST) {
                    width = Math.min(width, MeasureSpec.getSize(widthMeasureSpec));
                }
                super.onMeasure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                        heightMeasureSpec);
            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
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
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.changedCallback.onAttachedWindow();
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
    public void setAspectRatio(float ratio) {
        mAspectRatio = ratio;
        requestLayout();
    }

//    @Override
//    public void setSurfaceBufferSize(Point size) {
//        if(size.x == mWidth && size.y == mHeight) return;
//
//        mWidth = size.x;
//        mHeight = size.y;
//        getSurfaceTexture().setDefaultBufferSize(size.x, size.y);
//    }

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
