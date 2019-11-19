package com.example.mdmall.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdmall.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WishingWellAdapter extends RecyclerView.Adapter<WishingWellAdapter.MyViewHolder> {

    private Context context;
    private List<String> strings;

    public WishingWellAdapter(Context context, List<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.wishing_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.wishing_item_name.setText(strings.get(position));
        if (position%2==0){
            holder.wishing_item_linear.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.wishing_item_name)
        TextView wishing_item_name;
        @BindView(R.id.wishing_item_linear)
        LinearLayout wishing_item_linear;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
