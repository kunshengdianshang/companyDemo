package com.example.mdmall.bean;

/**
 * Created by Administrator on 2016/2/24.
 */
public class MyResult<T> {
    String state;
    String msg;
    T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
