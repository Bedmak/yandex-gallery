package com.example.yandex_gallery.ui;

import android.os.Bundle;

import com.example.yandex_gallery.R;
import com.example.yandex_gallery.ui.base.BaseActivity;
import com.example.yandex_gallery.ui.gallery.GalleryFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFrame, new GalleryFragment())
                    .commit();
        }
    }

    @Override
    protected int provideLayout() {
        return R.layout.activity_main;
    }

    public void inject() {
        getActivityComponent().inject(this);
    }
}
