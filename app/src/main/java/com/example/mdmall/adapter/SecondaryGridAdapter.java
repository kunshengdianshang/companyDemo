package com.example.mdmall.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mdmall.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondaryGridAdapter extends BaseAdapter {

    private List<String> data;
    private Context context;

    public SecondaryGridAdapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.secondary_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvGoodsName.setText(data.get(i));
        Glide.with(context).load(R.mipmap.ic_launcher).into(holder.ivGoodsImg);
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.secondary_item_img)
        ImageView ivGoodsImg;
        @BindView(R.id.secondary_item_text)
        TextView tvGoodsName;
        @BindView(R.id.secondary_item_linear)
        LinearLayout linearLayout;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
