package com.example.mdmall.interfaces;

import com.example.mdmall.bean.ShopCarBean;

import java.util.List;

public interface ShopCarInterface {
    //全选按钮选中判断
    void setShopCarAllImageChecked(boolean isSele);
    void isSelectIfShop(boolean isSele, int positon, int childPosition, List<ShopCarBean.ShopChildBean> li);
}
