package com.example.mdmall.adapter;

import android.app.Activity;
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

public class NsGvHandPickRecommendAdapter extends BaseAdapter {

    private List<String> data;
    private Activity context;

    public NsGvHandPickRecommendAdapter(List<String> data, Activity context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.rv_item_hand_pick_recommend, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(data.get(i));
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.iv_goods_name)
        TextView name;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
