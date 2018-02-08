package com.error_found.kotdroid.mpermission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user12 on 8/2/18.
 */

public  class BaseActivity extends AppCompatActivity implements ActivityCompat.
        OnRequestPermissionsResultCallback {
    protected int PERMISSION_REQUEST_READ_CONTACT = 20;
    protected int PERMISSION_REQUEST_write_CONTACT = 30;
    protected int MULTIPLE_PERMISSION = 40;

     final String[] multiplePermission =
            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.READ_CONTACTS};


    protected void requestRunTimePermission(final Activity activity, final String[] permissions,
                                            final int customPermissionConstant) {
        if (permissions.length == 1) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[0])) {
                Snackbar.make(findViewById(android.R.id.content), "Need permission to run"
                        , Snackbar.LENGTH_INDEFINITE).setAction("ENABLE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest
                                .permission.READ_CONTACTS}, customPermissionConstant);
                    }
                }).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permissions[0]}, customPermissionConstant);
            }
        } else if (permissions.length > 1 && customPermissionConstant == MULTIPLE_PERMISSION) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[0])) {
                    Snackbar.make(findViewById(android.R.id.content), "Need permission to run"
                            , Snackbar.LENGTH_INDEFINITE).setAction("ENABLE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat.requestPermissions(activity, multiplePermission, customPermissionConstant);
                        }
                    }).show();
                } else {
                    ActivityCompat.requestPermissions(this, multiplePermission, customPermissionConstant);
                }
        }
    }

    protected boolean checkMultiplePermissonAllowed() {
        for (String permission : multiplePermission) {
            if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED)
                return false;
        }
        return true;
    }

   /* protected String[] getDeniedPermission() {
        final List<String> deniedPermissions = new ArrayList<>();
        for (String permission : multiplePermission) {
            if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(permission);
            }
        }
        return (String[]) deniedPermissions.toArray();
    }*/

}
