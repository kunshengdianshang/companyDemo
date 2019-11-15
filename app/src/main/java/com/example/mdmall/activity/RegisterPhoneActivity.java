package com.example.mdmall.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterPhoneActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_phone);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.register_return,R.id.register_phone_img,R.id.login_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_return:
                finish();
                break;
            case R.id.register_phone_img:
                startActivity(new Intent(RegisterPhoneActivity.this,RegisterActivity.class));
                break;
            case R.id.login_go:
                startActivity(new Intent(RegisterPhoneActivity.this,LoginActivity.class));
                break;
        }
    }
}
