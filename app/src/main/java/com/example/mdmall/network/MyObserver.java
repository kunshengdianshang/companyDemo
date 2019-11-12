package com.example.mdmall.network;


import com.example.mdmall.bean.MyResult;
import com.orhanobut.logger.Logger;

import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;

/**
 * Created by Administrator on 2016/2/24 0024.
 */
public abstract class MyObserver<T> implements Observer<MyResult<T>> {

    @Override
    public void onCompleted() {
//        LogUtil.d("onCompleted");
    }

    @Override
    public void onError(Throwable e) {

//        LogUtil.d("onError");

        if (e instanceof HttpException) {
            HttpException response = (HttpException)e;
            int code = response.code();
            Logger.d("HttpException error code is  "+ code);
//            EventBus.getDefault().post(new HttpErrorEvent(true));
        }

        if(e instanceof UnknownHostException){
            UnknownHostException response = (UnknownHostException)e;
            Logger.d("UnknownHostException error is " + response.getMessage());
            //网络断开广播
//            EventBus.getDefault().post(new NetDisconnectEvent(true));
        }else {
            e.printStackTrace();
        }
    }

    @Override
    public void onNext(MyResult<T> t) {
        if(t.getState().equals("1")){
            onSuccess(t.getData());
        }else{
            onFail(t.getState(),t.getMsg());
        }
    }

    public void onSuccess(T response){
        Logger.d("onSuccess");
//        LogUtil.d("onSuccess");
    }

    public void onFail(String code, String msg){
        Logger.d("onFail code is " + code +",msg is " +  msg);

        //如果登陆超时
//        if(code.equals(ConstantValue.LOG_TIMEOUT)){
//            EventBus.getDefault().post(new LoginTimeoutEvent(true));
//        }
    }

}
