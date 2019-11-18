package com.example.mdmall.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdmall.R;
import com.example.mdmall.bean.PoiAddressBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PoiOtherMessageAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<PoiAddressBean> data=null;

    public PoiOtherMessageAdapter(Context context) {
        this.context = context;
        data=new ArrayList<>();
    }

    public void setData(List<PoiAddressBean> data){
        this.data=data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PoiOtherMessageViewHolder(View.inflate(context, R.layout.poi_other_message_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PoiOtherMessageViewHolder pmvh = (PoiOtherMessageViewHolder) holder;
        pmvh.text_location_name.setText(data.get(position).detailAddress+"");
        pmvh.text_location_address.setText(data.get(position).getText()+"");
    }

    @Override
    public int getItemCount() {
        if(data==null){
            return 0;
        }
        Log.d("AmapErr","检索走遍集合长度="+data.size());
        return data.size();
    }
    public class PoiOtherMessageViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text_location_name)
        TextView text_location_name;
        @BindView(R.id.text_location_address)
        TextView text_location_address;
        public PoiOtherMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
