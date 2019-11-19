package com.example.mdmall.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdmall.BaseFragment;
import com.example.mdmall.R;
import com.example.mdmall.adapter.ShopingCarAdapter;
import com.example.mdmall.adapter.WeiNiTuiJianAdapter;
import com.example.mdmall.bean.ShopCarBean;
import com.example.mdmall.interfaces.ShopCarInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoppingFragment extends BaseFragment implements ShopCarInterface {
    private static ShoppingFragment shoppingFragment;
    private View view;
    private ShopingCarAdapter shopingCarAdapter;
    private WeiNiTuiJianAdapter weiNiTuiJianAdapter;
    private ShopCarBean.ShopChildBean shopChildBean;

    public static ShoppingFragment getInstance() {
        if (shoppingFragment == null) shoppingFragment = new ShoppingFragment();
        return shoppingFragment;
    }

    public ShoppingFragment() {
    }

    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.recy_weinituijian)
    RecyclerView recy_weinituijian;

    @BindView(R.id.img_all)
    ImageView img_all;

    @OnClick(R.id.img_all)
    public void img_all_Click(){
        boolean b = ifImageIsOk(img_all, R.mipmap.icon_weixuanze);
        if(b){
            img_all.setImageResource(R.mipmap.icon_xuanzhong);
        }else{
            img_all.setImageResource(R.mipmap.icon_weixuanze);
        }
        upSelectStatus(b);
    }
    private List<ShopCarBean> list=null;
    // 判断当前ImageView
    private boolean ifImageIsOk(ImageView img,int drawables) {
        boolean equals = img.getDrawable().getConstantState().equals(getResources().getDrawable(drawables).getConstantState());
        return equals;
    }
    //全选改变选中状态
    public void upSelectStatus(boolean isSelec){

        for(int i = 0; i < list.size();i ++){
            //新建集合第一层bean
            ShopCarBean shopCarBean = new ShopCarBean();
            //改变第一层Bean的选中状态
            shopCarBean.setSelected(isSelec);
            for(int j = 0 ; j <list.get(i).getSelectedList().size();j++){
                //新建集合第二层bean
                ShopCarBean.ShopChildBean shopChildBean = new ShopCarBean.ShopChildBean();
                //改变第二层bean的选中状态
                shopChildBean.setChildSelect(isSelec);

                //新建的第二层Bean赋值给原 第二层Bean
                list.get(i).getSelectedList().set(j,shopChildBean);
                //新建第一层bean=赋值给原 第一层Bean
                shopCarBean.setSelectedList(list.get(i).getSelectedList());

            }
            //原集合替换新集合
            list.set(i,shopCarBean);
        }
        shopingCarAdapter.setData(list);
        shopingCarAdapter.notifyDataSetChanged();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                list=new ArrayList<>();
        if (view == null) view = inflater.inflate(R.layout.fragment_shopping, container, false);
        for(int i = 0 ; i < 3 ; i ++){
            ShopCarBean shopCarBean = new ShopCarBean();
            List<ShopCarBean.ShopChildBean> li=new ArrayList<>();
            for(int j = 0 ; j < 3 ; j ++){
                shopChildBean = new ShopCarBean.ShopChildBean();
                li.add(shopChildBean);
            }
            shopCarBean.setSelectedList(li);
            list.add(shopCarBean);
        }
        ButterKnife.bind(this, view);
       // EventBus.getDefault().register(this);
        initShopingCarAdapter();
        initWeiNiTuiJianAdapter();
        return view;
    }

    private void initWeiNiTuiJianAdapter() {
        recy_weinituijian.setNestedScrollingEnabled(false);
        if(weiNiTuiJianAdapter==null){
            weiNiTuiJianAdapter = new WeiNiTuiJianAdapter(getActivity());
            recy_weinituijian.setLayoutManager(new GridLayoutManager(getActivity(),2));
            recy_weinituijian.setAdapter(weiNiTuiJianAdapter);
        }
    }

    private void initShopingCarAdapter() {
        if(shopingCarAdapter==null){
            shopingCarAdapter = new ShopingCarAdapter(getActivity());
            recy.setLayoutManager(new LinearLayoutManager(getActivity()));
            shopingCarAdapter.setShopCarInterface(this);
            shopingCarAdapter.setData(list);
            recy.setAdapter(shopingCarAdapter);
        }
    }

    @Override
    public void setShopCarAllImageChecked(boolean isSele) {
        //判断全部商家选中 购物车的全选选中
        if(isSele){
            img_all.setImageResource(R.mipmap.icon_xuanzhong);
        }else{
            img_all.setImageResource(R.mipmap.icon_weixuanze);
        }
    }
    // 单个条目商家选中  子商品选中 亦然
    @Override
    public void isSelectIfShop(boolean isSele,int position,int childPositon,List<ShopCarBean.ShopChildBean> li) {
        //商家适配器调用
        if(childPositon==-1){
            ShopCarBean shopCarBean = list.get(position);
            shopCarBean.setSelected(isSele);
            for(int i = 0 ; i < list.size() ; i ++){
                ShopCarBean.ShopChildBean shopChildBean = list.get(position).getSelectedList().get(position);
                shopChildBean.setChildSelect(isSele);
                list.get(position).getSelectedList().set(i,shopChildBean);
            }
            list.set(position,shopCarBean);
            ifItemAllCheck();
            shopingCarAdapter.setData(list);
            shopingCarAdapter.notifyDataSetChanged();

        }else{
            //商家商品适配器调用
            //点击的对应商家的对应商品状态
            ShopCarBean.ShopChildBean shopChildBean = list.get(position).getSelectedList().get(childPositon);
            shopChildBean.setChildSelect(isSele);
            list.get(position).getSelectedList().set(childPositon,shopChildBean);


            int isSelect = 0;
            for(int i = 0; i < list.get(position).getSelectedList().size();i++){
                boolean childSelect = list.get(position).getSelectedList().get(i).isChildSelect();
                if(!childSelect){
                    isSelect++;
                }
            }
            ShopCarBean shopCarBean = list.get(position);
            if(isSelect>0){
                shopCarBean.setSelected(false);
            }else{
                shopCarBean.setSelected(true);
            }
            list.set(position,shopCarBean);
            ifItemAllCheck();
            shopingCarAdapter.setData(list);
            shopingCarAdapter.notifyDataSetChanged();
        }



    }
    //判断条目是否全选   全选按钮联动
    public void ifItemAllCheck(){



        int noSelect=0;
        for(int i=0;i<list.size();i++){
            boolean selected = list.get(i).isSelected();
            if(!selected){
                noSelect++;
            }
            if(noSelect>0){
                break;
            }
            for(int j = 0 ; j < list.get(i).getSelectedList().size();j++){

                boolean childSelect = list.get(i).getSelectedList().get(j).isChildSelect();
                if(!childSelect){
                    noSelect++;
                }
            }

        }
        if(noSelect>0){
            img_all.setImageResource(R.mipmap.icon_weixuanze);
        }else{
            img_all.setImageResource(R.mipmap.icon_xuanzhong);
        }

    }
}
