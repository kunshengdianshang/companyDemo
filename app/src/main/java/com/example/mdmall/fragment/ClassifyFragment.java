package com.example.mdmall.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mdmall.BaseFragment;
import com.example.mdmall.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

public class ClassifyFragment extends BaseFragment {
    private static ClassifyFragment classifyFragment;
    private View view;
    public static ClassifyFragment getInstance() {
        if (classifyFragment == null) classifyFragment = new ClassifyFragment();
        return classifyFragment;
    }

    public ClassifyFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) view = inflater.inflate(R.layout.fragment_classify, container, false);
        ButterKnife.bind(this, view);
       // EventBus.getDefault().register(this);
        return view;
    }
}
