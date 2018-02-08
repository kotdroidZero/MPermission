package com.error_found.kotdroid.mpermission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    public static final int EXTERNAL_STORAGE_REQUEST_PERMISSION = 100;
    public static final int CONTACT_REQUEST_PERMISSION = 200;
    public static final int ACCESS_LOCATION_PERMISSION = 300;
    public static final int ACESS_SMS_PERMISSION = 400;
    public static final int CAMERA_PERMISSION = 500;

    @BindView(R.id.btn_contact_permission)
    Button btnContactPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btnContactPermission.setOnClickListener(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions.length==1)
        {
            if (requestCode==CONTACT_REQUEST_PERMISSION&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {

            }
        }
    }

    @Override
    public void onClick(View view) {
        if (ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission
                .READ_CONTACTS)==PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this, "You already have the permission top access storage"
                    , Toast.LENGTH_SHORT).show();
            Log.e("TAG", "permission is granted");
        }
        else
        {
            requestRunTimePermission(MainActivity.this,new String[]
                    {Manifest.permission.READ_CONTACTS},CONTACT_REQUEST_PERMISSION);
        }
    }
}
