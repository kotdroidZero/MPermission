package com.error_found.kotdroid.mpermission;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.OnClick;

public class Main2Activity extends BaseActivity {

    public static final int EXTERNAL_STORAGE_REQUEST_PERMISSION = 100;
    public static final int CONTACT_REQUEST_PERMISSION = 200;
    public static final int ACCESS_LOCATION_PERMISSION = 300;
    public static final int ACESS_SMS_PERMISSION = 400;
    public static final int CAMERA_PERMISSION = 500;
    protected int MULTIPLE_PERMISSION = 40;

    final String[] multiplePermission =
            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.READ_CONTACTS};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @OnClick(R.id.btn_multiple_permissions)
    public void clickMultiple()
    {
        if (checkMultiplePermissonAllowed())
        {
            Toast.makeText(this, "Go ahead ,you have all the permissions",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            ActivityCompat.requestPermissions(this,multiplePermission,MULTIPLE_PERMISSION);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
