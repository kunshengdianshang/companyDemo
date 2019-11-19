package com.example.mdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdmall.R;
import com.example.mdmall.bean.ShopCarBean;
import com.example.mdmall.interfaces.ShopCarInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

class ShopingCarChildAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ShopCarBean.ShopChildBean> list=null;
    private ShopCarInterface shopCarInterface;
    private int index;
    public void setIndex(int index){
        this.index=index;
    }
    public void setShopCarInterface(ShopCarInterface shopCarInterface){
        this.shopCarInterface=shopCarInterface;
    }
    public ShopingCarChildAdapter(Context context) {
        this.context=context;
        this.list=new ArrayList<>();
    }
    public void setChildData(List<ShopCarBean.ShopChildBean> list){
        this.list=list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CollectedChildViewHolder(View.inflate(context, R.layout.shopingcar_child_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         CollectedChildViewHolder ccvh= (CollectedChildViewHolder) holder;
             ccvh.img_select.setVisibility(View.VISIBLE);
            if(list.get(position).isChildSelect()){
                ccvh.img_select.setImageResource(R.mipmap.icon_xuanzhong);
            }else{
                ccvh.img_select.setImageResource(R.mipmap.icon_weixuanze);
            }

            ccvh.img_select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(shopCarInterface==null){
                       return;
                   }
                   shopCarInterface.isSelectIfShop(!list.get(position).isChildSelect(),index,position,list);
                }
            });
            ccvh.text_jian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int shopCount = list.get(position).getShopCount();
                    if(shopCount==1){
                        return;
                    }
                    shopCount--;
                    ccvh.text_count.setText(shopCount+"");
                    ShopCarBean.ShopChildBean shopChildBean = list.get(position);
                    shopChildBean.setShopCount(shopCount);
                    list.set(position,shopChildBean);
                    notifyDataSetChanged();
                }
            });
          ccvh.text_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int shopCount = list.get(position).getShopCount();
                shopCount++;
                ccvh.text_count.setText(shopCount+"");
                ShopCarBean.ShopChildBean shopChildBean = list.get(position);
                shopChildBean.setShopCount(shopCount);
                list.set(position,shopChildBean);
                notifyDataSetChanged();
            }
          });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class CollectedChildViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_select)
        ImageView img_select;

        @BindView(R.id.text_jian)
        TextView text_jian;
        @BindView(R.id.text_jia)
        TextView text_jia;
        @BindView(R.id.text_count)
        TextView text_count;

        public CollectedChildViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
