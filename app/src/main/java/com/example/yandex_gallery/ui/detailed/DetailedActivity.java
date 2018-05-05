package com.example.yandex_gallery.ui.detailed;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.yandex_gallery.R;
import com.example.yandex_gallery.ui.base.BaseActivity;

import butterknife.BindView;

public class DetailedActivity extends BaseActivity {

    @BindView(R.id.detailedToolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFrame, new DetailedFragment())
                    .commit();
        }
    }

    private void initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        toolbar.setTitle("Detailed image");
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
