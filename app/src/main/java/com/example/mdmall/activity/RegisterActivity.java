package com.example.mdmall.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.register_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_return:
                finish();
                break;
        }
    }
}
