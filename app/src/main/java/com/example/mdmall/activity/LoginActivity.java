package com.example.mdmall.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_pass)
    EditText login_pass;
    @BindView(R.id.login_phone)
    EditText login_phone;
    @BindView(R.id.login_visible)
    ImageView login_visible;
    @BindView(R.id.login_code)
    TextView login_code;
    @BindView(R.id.login_text)
    TextView login_text;
    @BindView(R.id.login_quick)
    TextView login_quick;
    @BindView(R.id.login_but)
    ImageView login_but;
    private String a="0";//判断是密码还是验证码
    private String b="0";//密码是否可见
    private CountDownTimerUtils mCountDownTimerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        login_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        login_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())){
                    Glide.with(LoginActivity.this).load(R.mipmap.kedenglu).into(login_but);
                }else {
                    Glide.with(LoginActivity.this).load(R.mipmap.nodenglu).into(login_but);
                }
            }
        });

    }
    @OnClick({R.id.return_img,R.id.login_quick,R.id.login_new_user,R.id.login_visible,R.id.login_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.return_img://返回
                finish();
                break;
            case R.id.login_quick://密码/验证码登录切换
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
            case R.id.login_new_user://跳转注册
                startActivity(new Intent(LoginActivity.this,RegisterPhoneActivity.class));
                break;
            case R.id.login_visible://密码是否可见
                if (b.equals("0")){
                    Glide.with(LoginActivity.this).load(R.mipmap.xianshi).into(login_visible);
                    login_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    b="1";
                }else {
                    Glide.with(LoginActivity.this).load(R.mipmap.yinchang).into(login_visible);
                    login_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    b="0";
                }
                login_pass.setSelection(login_pass.getText().length());
                break;
            case R.id.login_code://验证码
                Log.e("TAG", "onViewClicked: 1" );
                mCountDownTimerUtils = new CountDownTimerUtils(login_code,60000,1000);
                mCountDownTimerUtils.start();
                break;
        }
    }
    //验证码倒计时
    public class CountDownTimerUtils extends CountDownTimer {
        private TextView mTextView;

        /**
         * @param textView          The TextView
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receiver
         *                          {@link #onTick(long)} callbacks.
         */
        public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            this.mTextView = textView;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTextView.setClickable(false); //设置不可点击
            mTextView.setText("(" + millisUntilFinished / 1000 + ")重新获取");//设置倒计时时间
            mTextView.setBackgroundResource(R.mipmap.huoqu_yanzhengma);
        }

        @Override
        public void onFinish() {
            mTextView.setText("重新发送");
            mTextView.setClickable(true);//重新获得点击
            mTextView.setBackgroundResource(R.mipmap.huoqu_yanzm);
        }
    }
    @Override
    protected void onDestroy() {
        if (mCountDownTimerUtils!=null) mCountDownTimerUtils.cancel();
        super.onDestroy();
    }
}
