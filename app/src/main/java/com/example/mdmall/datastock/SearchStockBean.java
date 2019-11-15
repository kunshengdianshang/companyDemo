package com.example.mdmall.datastock;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class SearchStockBean {
    @Id(autoincrement = true)//设置自增长
    private Long id;

    @Index(unique = true)//设置唯一性
    private String str;//搜索内容

    @Generated(hash = 62096561)
    public SearchStockBean(Long id, String str) {
        this.id = id;
        this.str = str;
    }

    @Generated(hash = 132788662)
    public SearchStockBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStr() {
        return this.str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
