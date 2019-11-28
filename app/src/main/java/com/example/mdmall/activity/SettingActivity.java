package com.example.mdmall.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;

import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

    }
}
