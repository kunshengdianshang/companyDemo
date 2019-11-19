package com.example.mdmall.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mdmall.BaseFragment;
import com.example.mdmall.R;
import com.example.mdmall.adapter.WishingWellAdapter;
import com.example.mdmall.view.AutoPollRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WishingWellFragment extends BaseFragment {
    @BindView(R.id.invitation_recycler)
    AutoPollRecyclerView recycler;
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
        InitExt();
        return view;
    }
    private void InitExt() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add("苏****夏");
        }
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        WishingWellAdapter wishingWellAdapter = new WishingWellAdapter(getContext(), list);
        recycler.setAdapter(wishingWellAdapter);
        recycler.start();
    }
}
