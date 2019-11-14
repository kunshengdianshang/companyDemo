package com.example.mdmall.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RelaNameActivity extends BaseActivity {

    //进度条三个文字
    @BindView(R.id.text_details_one)
    TextView text_details_one;
    @BindView(R.id.text_details_two)
    TextView text_details_two;
    @BindView(R.id.text_details_three)
    TextView text_details_three;

    //进度条
    @BindView(R.id.progress)
    ProgressBar progress;

    //三个文本
    @BindView(R.id.text_details_tianxie)
    TextView text_details_tianxie;
    @BindView(R.id.text_details_shangchuan)
    TextView text_details_shangchuan;
    @BindView(R.id.text_details_wancheng)
    TextView text_details_wancheng;

    @BindView(R.id.linear_edit)
    LinearLayout linear_edit;
    @BindView(R.id.linear_shangchuan)
    LinearLayout linear_shangchuan;
    @BindView(R.id.linear_ok)
    LinearLayout linear_ok;

    @BindView(R.id.but_ok)
    Button but_ok;
    @OnClick(R.id.but_ok)
    public void but_ok_Click(){
          if(status==1){
              status=2;
              text_details_one.setBackgroundResource(R.drawable.btn_radio_yellow);
              text_details_one.setTextColor(getResources().getColor(R.color.white));
              progress.setProgress(50);
              text_details_two.setBackgroundResource(R.drawable.btn_radio_yellow);
              text_details_two.setTextColor(getResources().getColor(R.color.white));

              linear_edit.setVisibility(View.GONE);
              linear_shangchuan.setVisibility(View.VISIBLE);
              linear_ok.setVisibility(View.GONE);

              but_ok.setText("立即上传");

              return;
          }
          if(status==2){
              progress.setProgress(100);
              text_details_three.setBackgroundResource(R.drawable.btn_radio_yellow);
              text_details_three.setTextColor(getResources().getColor(R.color.white));

              linear_edit.setVisibility(View.GONE);
              linear_shangchuan.setVisibility(View.GONE);
              linear_ok.setVisibility(View.VISIBLE);
          }

    }
    private int status=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rela_name);
        ButterKnife.bind(this);
    }
}
