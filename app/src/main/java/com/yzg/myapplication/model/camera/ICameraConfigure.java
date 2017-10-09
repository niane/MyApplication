package com.yzg.myapplication.model.camera;

/**
 * Created by yzg on 2017/9/29.
 */

public interface ICameraConfigure {
    void setPreviewCallback(ICameraHelper.PreviewCallback previewCallback);

    ICameraHelper.PreviewCallback getPreviewCallback();
}
