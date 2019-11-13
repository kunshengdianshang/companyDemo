package com.example.mdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectedAdapter extends RecyclerView.Adapter {
    private Context context;
    private int select=1;
    private CollectedChildAdapter collectedChildAdapter;

    public CollectedAdapter(Context context) {
        this.context = context;
    }
    public void setSelectStatus(int select){
        this.select=select;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CollectedViewHolder(View.inflate(context, R.layout.collect_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
          CollectedViewHolder cvh= (CollectedViewHolder) holder;
          cvh.recy.setLayoutManager(new LinearLayoutManager(context));
        collectedChildAdapter = new CollectedChildAdapter(context);
        if(select==1){
              cvh.img_select.setVisibility(View.GONE);
          }else{
              cvh.img_select.setVisibility(View.VISIBLE);
          }
          collectedChildAdapter.setSelectStatus(select);
          cvh.recy.setAdapter(collectedChildAdapter);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
    public class CollectedViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.recy)
        RecyclerView recy;
        @BindView(R.id.img_select)
        ImageView img_select;
        public CollectedViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
