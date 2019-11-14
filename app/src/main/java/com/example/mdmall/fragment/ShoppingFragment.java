package com.example.mdmall.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdmall.BaseFragment;
import com.example.mdmall.R;
import com.example.mdmall.adapter.ShopingCarAdapter;
import com.example.mdmall.adapter.WeiNiTuiJianAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingFragment extends BaseFragment {
    private static ShoppingFragment shoppingFragment;
    private View view;
    private ShopingCarAdapter shopingCarAdapter;
    private WeiNiTuiJianAdapter weiNiTuiJianAdapter;

    public static ShoppingFragment getInstance() {
        if (shoppingFragment == null) shoppingFragment = new ShoppingFragment();
        return shoppingFragment;
    }

    public ShoppingFragment() {
    }

    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.recy_weinituijian)
    RecyclerView recy_weinituijian;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) view = inflater.inflate(R.layout.fragment_shopping, container, false);
        ButterKnife.bind(this, view);
       // EventBus.getDefault().register(this);
        initShopingCarAdapter();
        initWeiNiTuiJianAdapter();
        return view;
    }

    private void initWeiNiTuiJianAdapter() {
        recy_weinituijian.setNestedScrollingEnabled(false);
        if(weiNiTuiJianAdapter==null){
            weiNiTuiJianAdapter = new WeiNiTuiJianAdapter(getActivity());
            recy_weinituijian.setLayoutManager(new GridLayoutManager(getActivity(),2));
            recy_weinituijian.setAdapter(weiNiTuiJianAdapter);
        }
    }

    private void initShopingCarAdapter() {
        if(shopingCarAdapter==null){
            shopingCarAdapter = new ShopingCarAdapter(getActivity());
            recy.setAdapter(shopingCarAdapter);
            recy.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }
}
