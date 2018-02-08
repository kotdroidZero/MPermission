package com.error_found.kotdroid.mpermission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main4Activity extends AppCompatActivity {


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
        setContentView(R.layout.activity_main4);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_multiple_permissions)
    public void clickMultiple() {
        if (checkVersion()) {
            if (checkMultiplePermissonAllowed()) {
                Toast.makeText(this, "U may proceed", Toast.LENGTH_SHORT).show();
            } else {

                ActivityCompat.requestPermissions(this, multiplePermission, MULTIPLE_PERMISSION);
            }
        }
    }

    private boolean checkVersion() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    protected boolean checkMultiplePermissonAllowed() {
        for (String permission : multiplePermission) {
            if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED)
                return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 40:
                boolean accessStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean accessCamera = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                boolean accessLocation = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                boolean accessSms = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                boolean accessContacts = grantResults[4] == PackageManager.PERMISSION_GRANTED;

                if (accessCamera && accessContacts && accessLocation && accessSms && accessStorage) {
                    Toast.makeText(this, "all permissions granted", Toast.LENGTH_SHORT).show();
                } else {
                    for (String permission : permissions) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(Main4Activity.this, permission)) {
                            Snackbar.make(findViewById(android.R.id.content), "Need permission to run"
                                    , Snackbar.LENGTH_INDEFINITE).setAction("ENABLE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ActivityCompat.requestPermissions(Main4Activity.this, new String[]{Manifest
                                            .permission.READ_CONTACTS}, MULTIPLE_PERMISSION);
                                }
                            }).show();
                        } else {
                            ActivityCompat.requestPermissions(this, permissions, MULTIPLE_PERMISSION);
                        }
                    }
                }
        }
    }
}
