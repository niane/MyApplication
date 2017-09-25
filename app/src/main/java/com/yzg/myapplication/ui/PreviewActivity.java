package com.yzg.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yzg.myapplication.R;
import com.yzg.myapplication.model.camera.CameraManager;
import com.yzg.myapplication.widget.AutoPreview;

/**
 * Created by yzg on 2017/9/25.
 */

public class PreviewActivity extends AppCompatActivity {
    private AutoPreview autoPreview;
    private CameraManager cameraManager;
    private Button btnJump;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_preview);
        autoPreview = (AutoPreview) findViewById(R.id.auto_preview);
        btnJump = (Button) findViewById(R.id.btn_jump);
        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PreviewActivity.this, ExampleMvp.class));
            }
        });

        cameraManager = new CameraManager(this);
        autoPreview.setCameraManager(cameraManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        autoPreview.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        autoPreview.onStop();
    }
}
