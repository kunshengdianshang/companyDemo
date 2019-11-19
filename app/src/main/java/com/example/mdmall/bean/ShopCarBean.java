package com.example.mdmall.bean;

import java.util.List;

public class ShopCarBean {
    private boolean isSelected = false;
    private List<ShopChildBean> selectedList;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public List<ShopChildBean> getSelectedList() {
        return selectedList;
    }

    public void setSelectedList(List<ShopChildBean> selectedList) {
        this.selectedList = selectedList;
    }

    public static class ShopChildBean {
        private boolean childSelect=false;
        private int shopCount=1;
        public boolean isChildSelect() {
            return childSelect;
        }

        public void setChildSelect(boolean childSelect) {
            this.childSelect = childSelect;
        }

        public int getShopCount() {
            return shopCount;
        }

        public void setShopCount(int shopCount) {
            this.shopCount = shopCount;
        }
    }
}
