package com.example.mdmall.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_pass)
    EditText login_pass;
    @BindView(R.id.login_visible)
    ImageView login_visible;
    @BindView(R.id.login_code)
    TextView login_code;
    @BindView(R.id.login_text)
    TextView login_text;
    @BindView(R.id.login_quick)
    TextView login_quick;

    private String a="0";//判断是密码还是验证码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.return_img,R.id.login_quick,R.id.login_new_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                finish();
                break;
            case R.id.login_quick:
                login_pass.setText("");
                if (a.equals("0")) {
                    login_pass.setHint("请输入验证码");
                    login_visible.setVisibility(View.GONE);
                    login_code.setVisibility(View.VISIBLE);
                    login_text.setText("无法登录");
                    login_quick.setText("账号密码登录");
                    a="1";
                }else {
                    login_pass.setHint("请输入密码");
                    login_visible.setVisibility(View.VISIBLE);
                    login_code.setVisibility(View.GONE);
                    login_text.setText("忘记密码");
                    login_quick.setText("手机快捷登录");
                    a="0";
                }
                break;
            case R.id.login_new_user:
                startActivity(new Intent(LoginActivity.this,RegisterPhoneActivity.class));
                break;
        }
    }
}
