package com.example.mdmall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mdmall.BaseFragment;
import com.example.mdmall.R;
import com.example.mdmall.activity.MyCollectedActivity;
import com.example.mdmall.activity.RelaNameActivity;

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
    @OnClick(R.id.text_shiming)
    public void text_shiming_Click(){
        Intent intent = new Intent(getActivity(), RelaNameActivity.class);
        startActivity(intent);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
       // EventBus.getDefault().register(this);

        return view;
    }
}
