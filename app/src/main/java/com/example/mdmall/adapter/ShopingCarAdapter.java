package com.example.mdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdmall.R;
import com.example.mdmall.bean.ShopCarBean;
import com.example.mdmall.interfaces.ShopCarInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopingCarAdapter extends RecyclerView.Adapter implements ShopCarInterface{
    private Context context;
    private ShopingCarChildAdapter shopingCarChildAdapter;
    private List<ShopCarBean> list=null;
    private ShopCarInterface shopCarInterface;
    public void setShopCarInterface(ShopCarInterface shopCarInterface){
        this.shopCarInterface=shopCarInterface;
    }
    public ShopingCarAdapter(Context context) {
        this.context = context;
        this.list=new ArrayList<>();
    }
    public void setData(List<ShopCarBean> list){
        this.list=list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CollectedViewHolder(View.inflate(context, R.layout.shopcar_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
              CollectedViewHolder cvh= (CollectedViewHolder) holder;
        if(list.get(position).isSelected()){
            cvh.img_select.setImageResource(R.mipmap.icon_xuanzhong);
        }else{
            cvh.img_select.setImageResource(R.mipmap.icon_weixuanze);
        }
        cvh.recy.setLayoutManager(new LinearLayoutManager(context));
        shopingCarChildAdapter = new ShopingCarChildAdapter(context);
        shopingCarChildAdapter.setChildData(list.get(position).getSelectedList());
        shopingCarChildAdapter.setShopCarInterface(this);
        shopingCarChildAdapter.setIndex(position);
        cvh.recy.setAdapter(shopingCarChildAdapter);
        cvh.img_select.setVisibility(View.VISIBLE);


        cvh.img_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopCarInterface==null){
                    return;
                }

                 if(list.get(position).isSelected()){
                    shopCarInterface.isSelectIfShop(!list.get(position).isSelected(),position,-1,null);
                 }else{
                     shopCarInterface.setShopCarAllImageChecked(false);
                     shopCarInterface.isSelectIfShop(!list.get(position).isSelected(),position,-1,null);

                 }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void setShopCarAllImageChecked(boolean isSele) {

    }

    @Override
    public void isSelectIfShop(boolean isSele, int positon,int childPosition,List<ShopCarBean.ShopChildBean> childList) {
               shopCarInterface.isSelectIfShop(isSele,positon,childPosition,childList);
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
