package com.example.mdmall.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mdmall.R;
import com.example.mdmall.activity.DetailsActivity;
import com.example.mdmall.activity.ProductListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RvFunctionBallAdapter extends RecyclerView.Adapter<RvFunctionBallAdapter.MyViewHolder> {

    private Activity context;
    private List<String> data;

    public RvFunctionBallAdapter(Activity context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rv_item_function_ball, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvName.setText(data.get(position));
        Glide.with(context).load(R.mipmap.ic_launcher).into(holder.ivImg);
       // holder.llItem.setLayoutParams(new LinearLayout.LayoutParams(UIutils.getWidth(context)/5, ViewGroup.LayoutParams.WRAP_CONTENT));
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductListActivity.open(context,""+holder.tvName.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.ll_item)
        LinearLayout llItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
