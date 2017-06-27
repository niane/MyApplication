package com.yzg.myapplication.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import java.util.HashMap;

/**
 * Created by qianxiaoai on 2016/7/7.
 */
public class PermissionUtils {

    private static final String TAG = PermissionUtils.class.getSimpleName();

    private static final HashMap<Integer, PermissionCallback> permissionCallbacks = new HashMap<>();

    public interface PermissionCallback {
        void onPermissionGranted(int requestCode);
        void onPermissionDenied(int requestCode);
    }

    public static boolean hasPermissions(Context context, @NonNull String... perms) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        if (context == null) {
            throw new IllegalArgumentException("Can't check permissions for null context");
        }

        for (String perm : perms) {
            if (ContextCompat.checkSelfPermission(context, perm)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    public static void requestPermission(@NonNull Activity activity, @NonNull String rationale,
                                         PermissionCallback permissionCallback, int requestCode,  @NonNull String... perms) {

        //如果是6.0以下的手机，ActivityCompat.checkSelfPermission()会始终等于PERMISSION_GRANTED，
        // 但是，如果用户关闭了你申请的权限，ActivityCompat.checkSelfPermission(),会导致程序崩溃(java.lang.RuntimeException: Unknown exception code: 1 msg null)，
        // 你可以使用try{}catch(){},处理异常，也可以判断系统版本，低于23就不申请权限，直接做你想做的。permissionGrant.onPermissionGranted(requestCode);
        if (Build.VERSION.SDK_INT < 23) {
            permissionCallback.onPermissionGranted(requestCode);
            return;
        }

        boolean shouldShowRationale = false;
        if(!hasPermissions(activity, perms)) {
            for (String perm : perms) {
                shouldShowRationale = ActivityCompat.shouldShowRequestPermissionRationale(activity, perm);
                if (shouldShowRationale) {
                    break;
                }
            }

            if (shouldShowRationale) {
                shouldShowRationale(activity, rationale, permissionCallback, requestCode, perms);
            }else {
                requestPermissions(activity, permissionCallback, requestCode, perms);
            }
        }else {
            permissionCallback.onPermissionGranted(requestCode);
        }
    }

    private static void requestPermissions(@NonNull Activity activity, PermissionCallback permissionCallback,
                                           int requestCode,  @NonNull String... perms){

        if(permissionCallbacks.get(requestCode) != null){
            permissionCallbacks.remove(requestCode);
        }

        permissionCallbacks.put(requestCode, permissionCallback);
        ActivityCompat.requestPermissions(activity, perms, requestCode);
    }

    private static void shouldShowRationale(@NonNull final Activity activity, @NonNull final String rationale,
                                            final PermissionCallback permissionCallback,
                                            final int requestCode, @NonNull final String... perms) {
        showMessageOKCancel(activity, rationale, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == DialogInterface.BUTTON_POSITIVE) {
                    requestPermissions(activity, permissionCallback, requestCode, perms);
                }else {
                    permissionCallback.onPermissionDenied(requestCode);
                }
            }
        });
    }

    private static void showMessageOKCancel(final Activity context, String message, DialogInterface.OnClickListener clickListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", clickListener)
                .setNegativeButton("Cancel", clickListener)
                .create()
                .show();

    }

    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        boolean grantResult = true;

        for(int result : grantResults){
            if (result != PackageManager.PERMISSION_GRANTED){
                grantResult = false;
                break;
            }
        }

        PermissionCallback callback = permissionCallbacks.remove(requestCode);
        if(callback != null){
            if(grantResult){
                callback.onPermissionGranted(requestCode);
            }else {
                callback.onPermissionDenied(requestCode);
            }
        }
    }

}
