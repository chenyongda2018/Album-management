package com.example.cyd.albummanager.Acitivty;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cyd.albummanager.R;
import com.example.cyd.albummanager.fragment.LoadAlbumFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(LoadAlbumFragment.TAG);
        if (fragment == null) {
            fragment = new LoadAlbumFragment();
        }
        final Fragment finalFragment = fragment;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.image_container, finalFragment).commit();
            }
        });
    }
}
