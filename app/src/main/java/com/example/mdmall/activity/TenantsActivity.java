package com.example.mdmall.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TenantsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenants);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.tenants_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tenants_return://返回
                finish();
                break;
        }
    }
}
