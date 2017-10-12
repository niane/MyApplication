package com.yzg.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.yzg.myapplication.R;
import com.yzg.myapplication.model.camera.CameraHelper;
import com.yzg.myapplication.model.camera.CameraManager;
import com.yzg.myapplication.widget.AutoPreview;
import com.yzg.myapplication.widget.SurfacePreview;
import com.yzg.myapplication.widget.TexturePreview;

/**
 * Created by yzg on 2017/9/25.
 */

public class PreviewActivity extends AppCompatActivity {
    private static final String Tag = PreviewActivity.class.getSimpleName();
    private TexturePreview autoPreview;
    private CameraManager cameraManager;
    private Button btnJump;
    private boolean isPreviewing = true;
    private CameraHelper cameraHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.frag_preview);
        autoPreview = (TexturePreview) findViewById(R.id.auto_preview);
        btnJump = (Button) findViewById(R.id.btn_jump);
        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = autoPreview.getLayoutParams();
                params.height = 720;
                params.width = 480;
                autoPreview.requestLayout();
//                startActivity(new Intent(PreviewActivity.this, ExampleMvp.class));
//                if(isPreviewing){
//                    cameraManager.stopPreview();
//                    isPreviewing = false;
//                }else {
//                    cameraManager.startPreview();
//                    isPreviewing = true;
//                }
            }
        });

        cameraHelper = CameraHelper.create(null, autoPreview);
//        cameraManager = new CameraManager(this);
//        autoPreview.setCameraManager(cameraManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        autoPreview.onResume();
        Log.e(Tag, "onresume");
        cameraHelper.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        autoPreview.onPause();
        Log.e(Tag, "onPause");
        cameraHelper.stop();
    }
}
