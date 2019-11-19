package com.example.mdmall.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.register_visible)
    ImageView register_visible;
    @BindView(R.id.register_pass)
    EditText register_pass;
    @BindView(R.id.register_code)
    EditText register_code;
    @BindView(R.id.register_but)
    ImageView register_but;
    @BindView(R.id.register_obtain)
    TextView register_obtain;
    private String b="0";//密码是否可见
    private CountDownTimerUtils mCountDownTimerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        register_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        register_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())){
                    Glide.with(RegisterActivity.this).load(R.mipmap.zhuce).into(register_but);
                }else {
                    Glide.with(RegisterActivity.this).load(R.mipmap.weizhuce).into(register_but);
                }
            }
        });
    }
    @OnClick({R.id.register_return,R.id.register_visible,R.id.register_obtain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_return:
                finish();
                break;
            case R.id.register_visible:
                if (b.equals("0")){
                    Glide.with(RegisterActivity.this).load(R.mipmap.xianshi).into(register_visible);
                    register_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    b="1";
                }else {
                    Glide.with(RegisterActivity.this).load(R.mipmap.yinchang).into(register_visible);
                    register_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    b="0";
                }
                register_pass.setSelection(register_pass.getText().length());
                break;
            case R.id.register_obtain://验证码
                mCountDownTimerUtils = new CountDownTimerUtils(register_obtain,60000,1000);
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
            Log.e("TAG", "millisInFuture: "+millisInFuture );
            this.mTextView = textView;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.e("TAG", "onViewClicked: "+millisUntilFinished );
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
