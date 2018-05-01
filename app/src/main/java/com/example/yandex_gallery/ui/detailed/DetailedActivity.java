package com.example.yandex_gallery.ui.detailed;

import android.os.Bundle;

import com.example.yandex_gallery.R;
import com.example.yandex_gallery.ui.base.BaseActivity;

public class DetailedActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFrame, new DetailedFragment())
                    .commit();
        }
    }

    @Override
    protected int provideLayout() {
        return R.layout.activity_detailed;
    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
    }
}
