package com.example.mdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class CollectedChildAdapter extends RecyclerView.Adapter {
    private Context context;
    private int select =1;
    public CollectedChildAdapter(Context context) {
        this.context=context;
    }
    public void setSelectStatus(int select){
        this.select=select;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CollectedChildViewHolder(View.inflate(context, R.layout.collect_child_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         CollectedChildViewHolder ccvh= (CollectedChildViewHolder) holder;
         if(select==1){
             ccvh.img_select.setVisibility(View.GONE);
         }else{
             ccvh.img_select.setVisibility(View.VISIBLE);
         }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
    public class CollectedChildViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_select)
        ImageView img_select;
        public CollectedChildViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
