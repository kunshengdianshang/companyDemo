package com.example.mdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdmall.R;

import butterknife.ButterKnife;

class AllDingDanChildAdapter extends RecyclerView.Adapter {
    private Context context;
    private int createStatus;
    public AllDingDanChildAdapter(Context context) {
        this.context = context;
    }
    public void setCreateStatus(int createStatus){
        this.createStatus=createStatus;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllDingDanChildViewHolder(View.inflate(context, R.layout.all_dingdan_adapter_child_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }
    public class AllDingDanChildViewHolder extends RecyclerView.ViewHolder{
        public AllDingDanChildViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
