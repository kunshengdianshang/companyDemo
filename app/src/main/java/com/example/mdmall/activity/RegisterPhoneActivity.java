package com.example.mdmall.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterPhoneActivity extends BaseActivity {
    @BindView(R.id.register_phone_edit)
    EditText register_phone_edit;
    @BindView(R.id.register_phone_img)
    ImageView register_phone_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_phone);
        ButterKnife.bind(this);
        register_phone_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())){
                    Glide.with(RegisterPhoneActivity.this).load(R.mipmap.xiayibu).into(register_phone_img);
                }else {
                    Glide.with(RegisterPhoneActivity.this).load(R.mipmap.xaiyibu).into(register_phone_img);
                }
            }
        });
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
