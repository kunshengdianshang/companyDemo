package com.example.mdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllDingDanAdapter extends RecyclerView.Adapter {
    private Context context;
    private AllDingDanChildAdapter allDingDanChildAdapter;
    //判断 全部1 待付款2 代发货3
    private int creatStatus;
    public void setCreatStatus(){
        this.creatStatus=creatStatus;
    };
    public AllDingDanAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllDingDanViewHolder(View.inflate(context, R.layout.all_dingdan_adapter_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AllDingDanViewHolder addvh= (AllDingDanViewHolder) holder;
        allDingDanChildAdapter = new AllDingDanChildAdapter(context);
        allDingDanChildAdapter.setCreateStatus(creatStatus);
        addvh.recy.setLayoutManager(new LinearLayoutManager(context));
        addvh.recy.setAdapter(allDingDanChildAdapter);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
    public class AllDingDanViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.recy)
        RecyclerView recy;
        public AllDingDanViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
