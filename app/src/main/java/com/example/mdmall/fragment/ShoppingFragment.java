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

public class ShoppingFragment extends BaseFragment {
    private static ShoppingFragment shoppingFragment;
    private View view;
    public static ShoppingFragment getInstance() {
        if (shoppingFragment == null) shoppingFragment = new ShoppingFragment();
        return shoppingFragment;
    }

    public ShoppingFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) view = inflater.inflate(R.layout.fragment_shopping, container, false);
        ButterKnife.bind(this, view);
       // EventBus.getDefault().register(this);
        return view;
    }
}
