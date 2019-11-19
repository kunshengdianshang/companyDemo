package com.example.mdmall.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mdmall.R;
import com.example.mdmall.activity.ProductListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {

    private Activity context;
    private List<String> data;
    private String a;
    public StoreAdapter(Activity context, List<String> data,String a) {
        this.context = context;
        this.data = data;
        this.a = a;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.store_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvName.setText(data.get(position));
        Glide.with(context).load(R.mipmap.ic_launcher).into(holder.ivImg);
        if (a.equals("1")){
            holder.box.setVisibility(View.GONE);
        }else if (a.equals("2")){
            holder.box.setVisibility(View.VISIBLE);
        }else if (a.equals("3")){
            holder.box.setChecked(true);
        }else {
            holder.box.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.store_item_img)
        ImageView ivImg;
        @BindView(R.id.store_item_name)
        TextView tvName;
        @BindView(R.id.store_item_box)
        CheckBox box;
        @BindView(R.id.store_item_linear)
        LinearLayout store_item_linear;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
