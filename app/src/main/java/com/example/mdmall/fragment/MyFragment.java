package com.example.mdmall.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mdmall.BaseFragment;
import com.example.mdmall.R;

import butterknife.ButterKnife;

public class MyFragment extends BaseFragment {
    private static MyFragment myFragment;
    private View view;
    public static MyFragment getInstance() {
        if (myFragment == null) myFragment = new MyFragment();
        return myFragment;
    }

    public MyFragment() {
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
