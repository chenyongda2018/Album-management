package com.example.cyd.albummanager.Acitivty;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cyd.albummanager.R;
import com.example.cyd.albummanager.fragment.LoadAlbumFragment;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_WRITE_EXTERNAL = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();

    }

    protected void loadFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(LoadAlbumFragment.TAG);
        if (fragment == null) {
            fragment = new LoadAlbumFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.image_container, fragment).commit();
    }

    //请求权限
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL);
        } else {
            loadFragment();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_EXTERNAL:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadFragment();
                }
                    break;
        }
    }
}
