package com.example.mdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdmall.R;
import com.example.mdmall.bean.ShopCarBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class WeiNiTuiJianAdapter extends RecyclerView.Adapter {
    private Context context;

    public WeiNiTuiJianAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeiNiTuiJianViewHolder(View.inflate(context, R.layout.weinituijian_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
          WeiNiTuiJianViewHolder wntjvh = (WeiNiTuiJianViewHolder) holder;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
    public class WeiNiTuiJianViewHolder extends RecyclerView.ViewHolder{
        public WeiNiTuiJianViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
