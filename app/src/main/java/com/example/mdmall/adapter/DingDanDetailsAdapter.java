package com.example.mdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdmall.R;
import com.example.mdmall.activity.DingDanDetailsActivity;

import butterknife.ButterKnife;

public class DingDanDetailsAdapter extends RecyclerView.Adapter {
    private Context context;

    public DingDanDetailsAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DingDanDetailsViewHolder(View.inflate(context, R.layout.dingdan_details_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            DingDanDetailsViewHolder dddvh= (DingDanDetailsViewHolder) holder;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
    class DingDanDetailsViewHolder extends RecyclerView.ViewHolder{
        public DingDanDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
