package com.example.mdmall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.mdmall.BaseFragment;
import com.example.mdmall.R;
import com.example.mdmall.activity.LoginActivity;
import com.example.mdmall.activity.DingDanActivity;
import com.example.mdmall.activity.MessageActivity;
import com.example.mdmall.activity.MyCollectedActivity;
import com.example.mdmall.activity.RelaNameActivity;
import com.example.mdmall.activity.SettingActivity;
import com.example.mdmall.activity.StoreActivity;
import com.example.mdmall.activity.TenantsActivity;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFragment extends BaseFragment {
    private static MyFragment myFragment;
    private View view;
    public static MyFragment getInstance() {
        if (myFragment == null) myFragment = new MyFragment();
        return myFragment;
    }

    public MyFragment() {
    }

    @BindView(R.id.text_shoucang)
    TextView text_shoucang;
    @OnClick(R.id.text_shoucang)
    public void setText_shoucangClick(){
        Intent intent = new Intent(getActivity(), MyCollectedActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.rela_real_name)
    public void text_shiming_Click(){
        Intent intent = new Intent(getActivity(), RelaNameActivity.class);
        startActivity(intent);
    }
    @OnClick({R.id.my_focus,R.id.rela_shop_in,R.id.my_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_focus:
                startActivity(new Intent(getActivity(), StoreActivity.class));
                break;
            case R.id.rela_shop_in:
                startActivity(new Intent(getActivity(), TenantsActivity.class));
                break;
            case R.id.my_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }
    @OnClick(R.id.text_lookAll)
    public void rela_wodedingdan_Click(){
        Intent intent = new Intent(getActivity(), DingDanActivity.class);
        startActivity(intent);
    };
    @BindView(R.id.rela_daifukuan)
    RelativeLayout rela_daifukuan;
    @BindView(R.id.rela_daishouhuo)
    RelativeLayout rela_daishouhuo;
    @BindView(R.id.rela_daipingjia)
    RelativeLayout rela_daipingjia;

    @OnClick(R.id.img_xiaoxi)
    public void img_xiaoxi_Click(){
        Intent intent = new Intent(getActivity(), MessageActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.img_setting)
    public void img_setting_Click(){
        Intent intent = new Intent(getActivity(), SettingActivity.class);
        startActivity(intent);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
