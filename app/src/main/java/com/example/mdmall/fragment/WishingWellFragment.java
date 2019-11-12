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

public class WishingWellFragment extends BaseFragment {
    private static WishingWellFragment wishingWellFragment;
    private View view;
    public static WishingWellFragment getInstance() {
        if (wishingWellFragment == null) wishingWellFragment = new WishingWellFragment();
        return wishingWellFragment;
    }

    public WishingWellFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) view = inflater.inflate(R.layout.fragment_wishing, container, false);
        ButterKnife.bind(this, view);
       // EventBus.getDefault().register(this);
        return view;
    }
}
