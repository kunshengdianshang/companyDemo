package com.example.mdmall.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdmall.BaseFragment;
import com.example.mdmall.R;
import com.example.mdmall.adapter.AllDingDanAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WaitPayFragment extends BaseFragment {

    private View view;
    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    private AllDingDanAdapter allDingDanAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.all_dingdan_fragment_layout,container,false);
        ButterKnife.bind(this, view);
        initRecyclerAdapter();
        return view;
    }

    private void initRecyclerAdapter() {
        if(allDingDanAdapter==null){
            allDingDanAdapter = new AllDingDanAdapter(getActivity());
            recy.setLayoutManager(new LinearLayoutManager(getActivity()));
            recy.setAdapter(allDingDanAdapter);
        }
    }

}
